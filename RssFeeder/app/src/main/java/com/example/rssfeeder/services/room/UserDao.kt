package com.example.rssfeeder.services.room

import androidx.room.*
import androidx.room.OnConflictStrategy.ABORT
import com.example.rssfeeder.services.model.User

@Dao
interface UserDao {
    @Insert(onConflict = ABORT)
    fun insertUser(user: User)

    @Query("SELECT * FROM user where email= :email and password= :password")
    fun getUser(email: String, password: String): User

    @Query("SELECT * FROM user where email=:email")
    fun findUser(email:String): User

    @Query("SELECT * FROM user")
    fun getAllUser(): List<User>

    @Delete()
    fun deleteUser(user: User)

    @Update
    fun updateUser(user: User)

    @Query("DELETE FROM user")
    fun deleteAllUser()

}