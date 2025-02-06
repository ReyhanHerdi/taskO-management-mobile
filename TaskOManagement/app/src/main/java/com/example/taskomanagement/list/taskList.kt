package com.example.taskomanagement.list

import com.example.taskomanagement.model.Tasks

fun taskList(): List<Tasks> {
    return listOf(
        Tasks(
            nameTask = "Mendapat Pacar",
            project = "Kehidupan Pribadi",
            description = "Mendapatkan pacar untuk valentine",
            due = "14 Februari 2025",
            status = "todo"
        ),
        Tasks(
            nameTask = "Menyelesaikan Skripsi",
            project = "Kewajiban",
            description = "Menyelesaikan skripsi agar cepat lulus",
            due = "1 April 2025",
            status = "ongoing"
        ),
        Tasks(
            nameTask = "Menyelesaikan Proyek",
            project = "Pengembangan Kemampuan",
            description = "Menyelesaikan proyek TaskO untuk portofolio",
            due = "30 April 2025",
            status = "done"
        ),
    )
}