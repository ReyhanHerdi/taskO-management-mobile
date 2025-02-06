package com.example.taskomanagement.list

import com.example.taskomanagement.model.Teams

fun teamList(): List<Teams> {
    return listOf(
        Teams(
            namaTeams = "Tim Single",
            projectSum = "5"
        ),
        Teams(
            namaTeams = "Tim 7",
            projectSum = "7"
        ),
        Teams(
            namaTeams = "Tim Marvel",
            projectSum = "10"
        )
    )
}