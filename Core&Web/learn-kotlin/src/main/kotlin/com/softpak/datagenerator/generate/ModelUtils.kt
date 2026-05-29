package com.softpak.datagenerator.generate

import com.softpak.datagenerator.utils.AppSettings
import com.softpak.datagenerator.utils.FileUtils
import com.softpak.datagenerator.utils.precision
import com.softpak.datagenerator.utils.rand
import edu.learn.jpa.beans.Firm
import java.io.File

class ModelUtils(private val firm: Firm?) {
    fun generateSecurityBasedModel(numberOfModels: Int) {
        for(counter in 1..numberOfModels){
            var modelIndex = FileUtils().readModelIndex() + 1
            var firmName = ""
            if(firm != null){
                firmName = firm.firmName + "/"
            }
            val securityBasedModelFile = File(firmName+"SECURITY_BASED_MODEL_$counter.csv")
            val list = PriceUtils().getRandomSecurities(AppSettings.getProperty("model.positions").toInt())
            val securities = mutableListOf<String>()
            //SECURITY_ID,MIN,MAX,TARGET
            var totalTarget = 100.00
            for(price in list){
                var target = rand(10.000,99.000/ AppSettings.getProperty("model.positions").toDouble()).toDouble()
                var min =   (80.00/100.00) * target
                var max =  (120.00/100.00) * target
                var row = "${price?.securityId},${precision(min,3)},${precision(max,3)},${precision((target ),3)}"
                totalTarget -= target
                securities.add(row)
            }

            val remainingTarget = totalTarget / AppSettings.getProperty("model.positions").toInt()
            for(i in 0..securities.size - 1){
                val row = securities.get(i).split(",")
                var addRemainingTarget = (row[3]).toDouble() + remainingTarget
                securityBasedModelFile.appendText("${row[0]},${row[1]},${row[2]},${precision(addRemainingTarget,3)}\n")
                FileUtils().writeModelIndex(modelIndex++)
            }
            //println(precision(totalTarget / AppSettings.getProperty("model.positions").toInt(),3))
        }
    }
}