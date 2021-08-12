package com.newcompany.andesofttest.viewmodel

import androidx.lifecycle.*
import com.newcompany.andesofttest.utils.Event
import com.newcompany.andesofttest.model.User
import com.newcompany.roomdatabasetask.repository.UserRepository
import kotlinx.coroutines.launch


class MainActivityViewModel( val userRepository2: UserRepository) : ViewModel() {
    val inputBookName = MutableLiveData<String>()
    var inputAuthorName = MutableLiveData<String>()
    val inputPrice = MutableLiveData<String>()
    val DOI = MutableLiveData<String>()
    var imageList = MutableLiveData<String>()
    val save = MutableLiveData<String>()
    private val statusMessage = MutableLiveData<Event<String>>()
    val message:LiveData<Event<String>> get()=statusMessage
    val quantityEntries: List<String> = getStaticSpinnerData()


    fun getStaticSpinnerData():List<String>
    {

       return listOf("Select Author...", "Author A", "Author B", "Author C")
    }



    var allUsers: MutableLiveData<List<User>> = MutableLiveData()

    init {
        inputAuthorName.value="Select Book..."
        save.value="Save"

    }
   

    fun onSaveButtonClick()
    {
        if (inputBookName.value == null || inputBookName.value == "") {
            statusMessage.value = Event("Please Enter Book Name.")
        } else if (inputAuthorName.value == null || inputAuthorName.value == "Select Author...") {
            statusMessage.value = Event("Please Select Author Name.")
        } else if (inputPrice.value==null || inputPrice.value=="") {
            statusMessage.value = Event("Please Enter Price.")
        }
        else if (DOI.value==null || DOI.value=="") {
            statusMessage.value = Event("Please Select DOI.")
        }
    else if (imageList.value==null || imageList.value=="") {
        statusMessage.value = Event("Please Add Thumb Cover")
    }
        else {
            val inputBookName=inputBookName.value!!
            val inputAuthorName=inputAuthorName.value!!
            val inputPrice=inputPrice.value!!
            val DOI=DOI.value!!
            val imageList=imageList.value!!
            saveUser(User(0, inputBookName, inputAuthorName, inputPrice,DOI, imageList))

        }
    }


      fun saveUser(user: User)
     =viewModelScope.launch {
         val newRowId =userRepository2.insertUser(user)
          if (newRowId > -1) {
           
              statusMessage.value = Event("User Inserted Successfully $newRowId")
              inputBookName.value=""
              inputAuthorName.value="Select Author..."
              inputPrice.value=""
              DOI.value=""

              imageList.value=""
              
          } else {
              statusMessage.value = Event("Error Occurred")
          }

     }
    

     fun getUsers() = userRepository2.getAllUsers()

    fun imageUpload(stringImage: String?)
    {

        imageList.value= stringImage!!
        statusMessage.value= Event("Image Uploaded suucessfully")
    }


}