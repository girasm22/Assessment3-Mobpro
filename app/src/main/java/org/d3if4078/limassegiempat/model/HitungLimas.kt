package org.d3if4078.limassegiempat.model

import org.d3if4078.limassegiempat.db.LimasEntity

fun LimasEntity.hitungLimas(): HasilLimas {
    val limas = (editPanjang * editLebar * editTinggi) / 3
    return HasilLimas(limas)
}
