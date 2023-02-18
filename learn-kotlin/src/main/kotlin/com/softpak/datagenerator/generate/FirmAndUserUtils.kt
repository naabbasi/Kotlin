package com.softpak.datagenerator.generate

import com.softpak.datagenerator.Options
import com.softpak.datagenerator.utils.FileUtils

import edu.learn.jpa.beans.Firm
import edu.learn.jpa.beans.SetupApplication
import java.io.File

class FirmAndUserUtils(private val option: Options) {

    companion object {
        private var numberOfFirms : Int = 0
        private var numberOfUsers : Int = 0
        private var numberOfAccounts : Int = 0
        private var numberOfModels : Int = 0
    }

    fun generate(){
        when(option){
            Options.ACCOUNTS_ONLY -> {
                getAccountInput()
                createAccountsOnly()
                reset()
            }
            Options.MODEL_ONLY -> {
                getModelInput()
                createModelsOnly()
                reset()
            }
            Options.FIRMS_USERS -> {
                getFirmUsersInput()
                createFirms()
                reset()
            }
            Options.ALL -> {
                getFirmUsersInput()
                getAccountInput()
                getModelInput()
                createFirms()
                reset()
            }
        }
    }

    private fun createFirms() {
        val setup = SetupApplication()
        var firmIndex = FileUtils().readFirmIndex() + 1

        for(i in 1..numberOfFirms){
            var firmName = "firm0" + firmIndex++
            val firm : Firm = setup.createFirms(firmName)
            createUsers(firm = firm,numberOfUsers = numberOfUsers)

            if(numberOfAccounts != 0 && numberOfModels != 0){
                File(firm.firmName.toUpperCase()).mkdirs()
                AccountUtils(firm).generateAccounts(numberOfAccounts)
                ModelUtils(firm).generateSecurityBasedModel(numberOfModels)
            }

            FileUtils().writeFirmIndex(firmIndex)
        }
    }

    private fun createAccountsOnly(){
        print("Enter prefix for account name: ")
        val prefix = readLine()!!.trim().toUpperCase()
        if(prefix != ""){
            val firm = Firm()
            firm.firmName = prefix
            File(firm.firmName.toUpperCase()).mkdirs()
            AccountUtils(firm).generateAccounts(numberOfAccounts)
        } else createAccountsOnly()
    }

    private fun createModelsOnly(){
        ModelUtils(null).generateSecurityBasedModel(numberOfModels)
    }

    private fun reset() {
        numberOfFirms = 0
        numberOfUsers = 0
        numberOfAccounts = 0
        numberOfModels = 0
    }

    private fun createUsers(numberOfUsers: Int, firm: Firm){
        val setup = SetupApplication()
        for(i in 1..numberOfUsers){
            setup.createUsers(firm, firm.firmName+"user-0" + i)
        }
    }

    fun getFirmUsersInput(){
        try {
            if(Companion.numberOfFirms == 0 && Companion.numberOfUsers == 0){
                print("Please enter number of firms and users e.g. (2 5) : ")
                val (getNumberOfFirms, getNumberOfUsers) = readLine()!!.trim().split(' ').map(String::toInt)

                if(getNumberOfFirms == 0 || getNumberOfUsers == 0) {
                    println("Firms or Users can't be 0")
                    getFirmUsersInput()
                }else{
                    Companion.numberOfFirms = getNumberOfFirms
                    Companion.numberOfUsers = getNumberOfUsers
                }
            }
        }catch (ex: Exception){
            when(ex) {
                is NumberFormatException -> getFirmUsersInput()
                is ArrayIndexOutOfBoundsException -> getFirmUsersInput()
            }
        }
    }

    private fun getAccountInput(){
        try {
            if(Companion.numberOfAccounts == 0){
                print("Enter number of accounts in each firm e.g. (5) : ")
                var numberOfAccounts = readLine()!!.trim().toInt()
                if(numberOfAccounts <= 0) {
                    println("Account(s) can't be 0")
                    getAccountInput()
                } else {
                    Companion.numberOfAccounts = numberOfAccounts
                }
            }
        }catch (ex: Exception){
            when(ex) {
                is NumberFormatException -> getAccountInput()
            }
        }
    }

    private fun getModelInput() {
        try {
            if (Companion.numberOfModels == 0) {
                print("Enter number of models in each firm e.g. (5) : ")
                var numberOfModels = readLine()!!.trim().toInt()
                if (numberOfModels <= 0) {
                    println("Model(s) can't be 0")
                    getModelInput()
                } else {
                    Companion.numberOfModels = numberOfModels
                }
            }
        }catch (ex: Exception){
            when(ex) {
                is NumberFormatException -> getModelInput()
            }
        }
    }
}