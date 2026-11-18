package com.tunombretuapellido.dicegame.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/** Tests de las reglas del juego (las tiradas se fijan para que sean deterministas). */
class GameRulesTest {

    @Test
    fun diceRollIsBetweenOneAndSix() {
        val rolls = List(1000) { Dice.roll() }
        assertTrue(rolls.all { it in 1..6 })
        assertEquals(6, rolls.toSet().size)
    }

    @Test
    fun higherWinsThreePointsMinusCost() {
        val result = GameRules.play(points = 20, bet = Bet.HIGHER, previous = 3, roll = 5)
        assertTrue(result.won)
        assertEquals(22, result.points)
    }

    @Test
    fun equalRollLosesArrowBets() {
        assertFalse(GameRules.play(20, Bet.HIGHER, previous = 4, roll = 4).won)
        assertFalse(GameRules.play(20, Bet.LOWER, previous = 4, roll = 4).won)
        assertEquals(19, GameRules.play(20, Bet.LOWER, previous = 4, roll = 4).points)
    }

    @Test
    fun numberBetPaysFifteen() {
        val win = GameRules.play(points = 20, bet = Bet.NUMBER, previous = 1, number = 6, roll = 6)
        assertEquals(32, win.points)
        val lose = GameRules.play(points = 20, bet = Bet.NUMBER, previous = 1, number = 6, roll = 2)
        assertEquals(17, lose.points)
    }

    @Test
    fun cannotBetWithoutEnoughPoints() {
        assertFalse(GameRules.canAfford(2, Bet.NUMBER))
        assertTrue(GameRules.canAfford(1, Bet.HIGHER))
        assertFalse(GameRules.canAfford(0, Bet.LOWER))
    }

    @Test(expected = IllegalArgumentException::class)
    fun playingWithoutPointsThrows() {
        GameRules.play(points = 0, bet = Bet.HIGHER, previous = 3)
    }

    @Test
    fun pointsNeverNegative() {
        var points = GameRules.INITIAL_POINTS
        var previous = Dice.roll()
        while (!GameRules.isGameOver(points)) {
            val bet = if (GameRules.canAfford(points, Bet.NUMBER)) Bet.NUMBER else Bet.LOWER
            val safeBet = if (GameRules.isPossible(bet, previous)) bet else Bet.HIGHER
            val result = GameRules.play(points, safeBet, previous, number = 1)
            assertTrue(result.points >= 0)
            points = result.points
            previous = result.roll
            if (points > 1000) break
        }
    }

    @Test
    fun impossibleArrows() {
        assertFalse(GameRules.isPossible(Bet.HIGHER, 6))
        assertFalse(GameRules.isPossible(Bet.LOWER, 1))
        assertTrue(GameRules.isPossible(Bet.NUMBER, 6))
    }
}
