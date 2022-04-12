package fr.insideapp.turnipoff.utils

import fr.insideapp.turnipoff.network.TheMovieDBClient
import java.time.LocalDate

fun LocalDate.formatForService(): String {
    return this.format(TheMovieDBClient.formatter)
}