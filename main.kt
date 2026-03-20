fun main() {
    val tasks = mutableListOf<String>()
    // val compeleted = mutableListOf<String>()
    
    while (true){
        println("=== Task Tracker ===")
        println("1. Add task")
        println("2. Remove tasks")
        println("3. View tasks")
        println("4. Exit")
        print("Choose an option: ")
        val choice = readln()

        when(choice){
            "1" -> addTask(tasks)
            "2" -> removeTask(tasks)
            "3" -> viewTasks(tasks)
            "4" -> {println("Bye")
                    return}
            else -> println("Invalid option")
        }
    }
}
fun addTask(tasks: MutableList<String>){
    println("Add a task: ")
    val task = readln()
    tasks.add(task)
    println("Task was added")
}

fun viewTasks(tasks: List<String>) {
    println("Your tasks:\n")
    if(tasks.isEmpty()) {
        println("\t-No tasks yet-")
    } else {
        tasks.forEachIndexed { 
            index, task -> println("${index + 1}. $task")
        }
        print("\n")
    }
}
fun removeTask(tasks: MutableList<String>){
    if(tasks.isEmpty()){
        println("\t-No tasks yet-")
        return
    }else{
        println("Which task to remove:\n")
        tasks.forEachIndexed { 
            index, task -> println("${index + 1}. $task")
        }
        print("Choose an option: ")
        val choice = readln().toIntOrNull()
        if(choice == null){
            println("Invald task")
            return
        }else if(choice <= 0 || choice > tasks.size){
            println("No task there")
            return            
        }else{
            tasks.removeAt(choice-1)
            println("Task was removed")
        }
    }
}

// println("coming soon")

