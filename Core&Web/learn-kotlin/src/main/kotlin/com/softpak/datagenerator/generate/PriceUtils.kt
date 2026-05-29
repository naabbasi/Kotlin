package com.softpak.datagenerator.generate



import com.softpak.datagenerator.utils.rand
import java.io.File
import java.io.FileNotFoundException
import java.util.*

data class Price(val securityId: String, val price: Double, val date: Date)

class PriceUtils {
    companion object {
        private val map = mutableMapOf<Int,Price>()
        fun getPriceMap(): SortedMap<Int, Price> {
            if(map.isEmpty()){
                return readPrice().toSortedMap()
            }else{
                return map.toSortedMap()
            }
        }

        private fun readPrice() : Map<Int, Price> {
            try{
                val priceFile = File("price.sql")
                var index = 0
                priceFile.useLines { lines -> lines.forEach {
                    var row = it.replace("to_date","")
                    var securitId = (row.split(",")[0]).replace("'","")
                    var price = (row.split(",")[1]).toDouble()
                    var date = (row.split(",")[2]).replace("(","").replace("'","");
                    map.put(index++, Price(securitId, price, Date(date)))
                } }
            }catch (fnf : FileNotFoundException){
                println("Please put price.sql file")
                System.exit(1)
            }


            return map
        }
    }

    fun getRandomSecurities(numberOfSecurities : Int) : MutableList<Price?> {
        val list = mutableListOf<Price?>()
        for(i in 1..numberOfSecurities){
            list.add(getPriceMap().get(rand(0, getPriceMap().size)))
        }

        return list
    }
}