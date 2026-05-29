package com.softpak.datagenerator.generate

import com.softpak.datagenerator.utils.AppSettings
import com.softpak.datagenerator.utils.FileUtils
import com.softpak.datagenerator.utils.rand
import com.softpak.datagenerator.utils.randomDate
import edu.learn.jpa.beans.Firm

import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AccountUtils(private val firm: Firm) {
    fun generateAccounts(numberOfAccounts: Int) {
        val firmName = if(firm.firmName == "") "" else firm.firmName.toUpperCase()
        var accountIndex = FileUtils().readAccountIndex() + 1
        val accountsFile = File(firmName + "/ACCOUNTS.csv")

        if(numberOfAccounts > 0)
            println("Writing account(s)")

        for (i in 1..numberOfAccounts) {
            var accountId = "${firmName}_ACCOUNT_0000$accountIndex"
            var accountName = "${firmName}_ACCOUNT_0000$accountIndex"
            var custodianName = "${firmName}_CUSTODIAN_0000$accountIndex"
            var row = "$accountId,$accountName,$custodianName\n"

            generateAccountPositions(accountId)
            accountsFile.appendText("$row")
            FileUtils().writeAccountIndex(accountIndex++)
        }
    }

    private fun generateAccountPositions(accountId: String){
        val firmName = firm.firmName.toUpperCase()
        val numberOfPositions = AppSettings.getProperty("account.positions").toInt()
        val positionsFile = File(firmName + "/ACCOUNT_POSITIONS.csv")

        if(numberOfPositions > 0)
            println("Writing $accountId position(s)")

        val list : MutableList<Price?> = PriceUtils().getRandomSecurities(numberOfPositions)
        //ACCOUNT_ID,SECURITY,NUMBER_OF_SHARES,TRADABLE,BASIS_PRICE,DATE_OF_PURCHASE
        var row = ""
        for(price in list){
            row += "$accountId,${price?.securityId},${rand(1.000,99.000)},${rand(0,1)},${rand(1.000,99.000,2)},${randomDate(AppSettings.getProperty("date.start").toInt(),AppSettings.getProperty("date.end").toInt(),AppSettings.getProperty("date.format"))}\n"
        }

        positionsFile.appendText("$row")

        if(AppSettings.getProperty("account.restrictions").toInt() > 0){
            generateAccountRestrictions(accountId, list)
        }

        if(AppSettings.getProperty("account.restrictions").toInt() > 0){
            generateAccountWashsales(accountId, list)
        }
    }

    private fun generateAccountRestrictions(accountId: String, list: MutableList<Price?>){
        val firmName = firm.firmName.toUpperCase()
        val numberOfRestrictions = AppSettings.getProperty("account.restrictions").toInt()
        val restrictionsFile = File(firmName + "/ACCOUNT_RESTRICTIONS.csv")

        if(numberOfRestrictions > 0)
            println("Writing $accountId restriction(s)")

        //ACCOUNT_ID,SECURITY,MIN,MAX,RESTRICTION_TYPE
        var row = ""
        for(i in 1..numberOfRestrictions){
            var price = list.get(rand(1,list.size))
            row += "$accountId,${price?.securityId},${rand(1.000,49.000)},${rand(50.000,99.000)},${rand(1.000,99.000)},${rand(1,6)}\n"
        }

        restrictionsFile.appendText("$row")
    }

    private fun generateAccountWashsales(accountId: String, list: MutableList<Price?>){
        val firmName = firm.firmName.toUpperCase()
        val numberOfWashsales = AppSettings.getProperty("account.restrictions").toInt()
        val washsalesFile = File(firmName + "/ACCOUNT_WASHSALES.csv")
        val formatter = DateTimeFormatter.ofPattern(AppSettings.getProperty("date.format"))

        val now = LocalDate.now()
        val thirtyDaysBack = now.minusDays(30).format(formatter)

        if(numberOfWashsales > 0)
            println("Writing $accountId washsales")

        //ACCOUNT_ID,SECURITY,DATE(30 days)
        var row = ""
        for(i in 1..numberOfWashsales){
            var price = list.get(rand(1,list.size))
            row = "$accountId,${price?.securityId},$thirtyDaysBack\n"
        }

        washsalesFile.appendText("$row")
    }
}