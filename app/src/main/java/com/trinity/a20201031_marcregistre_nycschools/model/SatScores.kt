package com.trinity.a20201031_marcregistre_nycschools.model

// using kotlin to reduce the amount of code that would have to be written if java was used.
data class SatScores(
        val dbn: String? = null,
        val school_name: String? = null,
        val num_of_sat_test_takers: String? = null,
        val sat_critical_reading_avg_score: String? = null,
        val sat_math_avg_score: String? = null,
        val sat_writing_avg_score: String? = null
)
