package com.newcompany.andesofttest.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "user_table")
   data class User(
      @PrimaryKey(autoGenerate = true)
       @ColumnInfo(name = "user_id")
               var  user_id: Int,
       @ColumnInfo(name = "bookName")
               var inpuBookName: String,
       @ColumnInfo(name ="AuthorName")
               var inputAuthorName: String,
       @ColumnInfo(name = "Price")
               var inputPrice: String,
      @ColumnInfo(name = "DOI")
      var DOI: String,
       @ColumnInfo(name = "imageList")
      var image: String

   )