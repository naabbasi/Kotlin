<pre>
    fun main(args: Array<String>) {
        println("Hello World")
    
        for(i in 1..10 step 2){
            println("Hello World " + i)
        }
    
        val list = mutableListOf<String>()
        list.add("Hello World 1....")
        list.add("Hello World 2....")
        list.add("Hello World 3....")
        list.add("Hello World 4....")
        list.add("Hello World 5....")
        
        println(list)
    }
</pre>

Download native compiler from https://github.com/JetBrains/kotlin-native/releases
 - kotlin-native-windows-0.6
 - set KOTLIN_NATIVE in environment variable
 - Downloaded kotlin native dependencies at following path
   - C:\Users\nabbasi\.konan\dependencies
 - compile code with konanc/kotlinc HelloWorld.kt -o HelloWorld.exe -opt
 - "-opt" For an optimized compilation

Download kotlin compile from https://github.com/JetBrains/kotlin/releases/tag/v1.2.21 
 - %KOTLIN%\kotlinc HelloWorld.kt   //For jvm
 - %KOTLIN%\kotlinc-js HelloWorld.kt -output HelloWorld.js