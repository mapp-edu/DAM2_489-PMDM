package com.tunombretuapellido.basket3x3.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/** Tests de las reglas del marcador. */
class MatchStateTest {

    @Test
    fun pointsAreAddedToTheRightTeam() {
        val match = MatchState().addPoints(Team.HOME, 2).addPoints(Team.AWAY, 1)
        assertEquals(2, match.home.score)
        assertEquals(1, match.away.score)
    }

    @Test
    fun foulsStopAtSevenAndBonusStarts() {
        var match = MatchState()
        repeat(6) { match = match.addFoul(Team.AWAY) }
        assertFalse(match.away.inBonus)
        repeat(3) { match = match.addFoul(Team.AWAY) }
        assertEquals(MatchRules.MAX_FOULS, match.away.fouls)
        assertTrue(match.away.inBonus)
    }

    @Test
    fun foulsNeverNegative() {
        assertEquals(0, MatchState().removeFoul(Team.HOME).home.fouls)
    }

    @Test
    fun reachingTwentyOneWinsAndFreezesTheMatch() {
        var match = MatchState()
        repeat(10) { match = match.addPoints(Team.HOME, 2) }
        assertNull(match.winner)
        match = match.addPoints(Team.HOME, 2)          // 22 ≥ 21
        assertEquals(Team.HOME, match.winner)
        assertTrue(match.isFinished)
        assertEquals(match, match.addPoints(Team.AWAY, 1))
        assertEquals(match, match.addFoul(Team.AWAY))
    }

    @Test(expected = IllegalArgumentException::class)
    fun threePointersDoNotExist() {
        MatchState().addPoints(Team.HOME, 3)
    }

    @Test
    fun intListRoundTrip() {
        val match = MatchState(TeamState(15, 7), TeamState(9, 2))
        assertEquals(match, MatchState.fromIntList(match.toIntList()))
    }
}
