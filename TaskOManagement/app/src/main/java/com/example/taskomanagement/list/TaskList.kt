package com.example.taskomanagement.list

import com.example.taskomanagement.model.Tasks

fun TaskList(): List<Tasks> {
    return listOf(
        Tasks(
            nameTask = "Mendapat Pacar",
            description = "Mendapatkan pacar untuk valentine",
            due = "14 Februari 2025",
            status = "ongoing"
        ),
        Tasks(
            nameTask = "Menyelesaikan Skripsi",
            description = "Menyelesaikan skripsi agar cepat lulus",
            due = "1 April 2025",
            status = "ongoing"
        ),
        Tasks(
            nameTask = "Menyelesaikan Proyek",
            description = "Menyelesaikan proyek TaskO untuk portofolio",
            due = "30 April 2025",
            status = "ongoing"
        ),
    )
}