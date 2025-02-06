package com.example.taskomanagement.list

import com.example.taskomanagement.model.Projects

fun projectsList(): List<Projects> {
    return listOf(
        Projects(
            nameProject = "Kehidupan Pribadi",
            description = "Proyek yang berkaitan dengan kehidupan pribadi",
            due = "14 Februari 2025",
            status = "ongoing",
            nameTeam = "Team Single"
        ),
        Projects(
            nameProject = "Kewajiban",
            description = "Proyek wajib yang perlu dieselesaikan",
            due = "30 Aptil 2025",
            status = "ongoing",
            nameTeam = "Team 7"
        ),
        Projects(
            nameProject = "Pengembangan Kemampuan",
            description = "Proyek sampingan untuk mengembangkan kemampuan pribadi",
            due = "12 April 2025",
            status = "ongoing",
            nameTeam = "Team 7"
        ),
    )
}