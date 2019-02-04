package com.example.rssfeeder.dao


import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.example.rssfeeder.services.model.User
import com.example.rssfeeder.services.room.UserDao
import com.example.rssfeeder.services.room.UserDatabase
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4ClassRunner::class)
class UserDaoTest {

    private lateinit var userDao: UserDao
    private lateinit var db: UserDatabase

    @Before
    @Throws(IOException::class)
    fun initDb(){

        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().targetContext,
            UserDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        userDao = db.userDao()
    }



    @Test
    fun writeAndReadUserTest(){

        userDao.insertUser(user)

        val byEmail = userDao.findUser("deepak.charya@gmail.com")
        Assert.assertEquals(byEmail.email, "deepak.charya@gmail.com")
    }

    @Test
    fun testEmptyRecord(){
        val userList = userDao.getAllUser()
        Assert.assertTrue(userList.isEmpty())
    }

    @Test
    fun testDelete(){
        userDao.deleteUser(user)
        val user = userDao.findUser(user.email)
        Assert.assertTrue(user == null)
    }

    @After
    @Throws(Exception::class)
    fun closeDB(){
        db.close()
    }
    companion object {
        val user = User(
            "Deepak",
            "deepak.charya@gmail.com",
            "9988851509",
            "diya@1234"
        )
    }

}