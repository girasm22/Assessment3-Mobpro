package org.d3if4078.limassegiempat.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "limas")
data class LimasEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var editPanjang: Float,
    var editLebar: Float,
    var editTinggi: Float
)

