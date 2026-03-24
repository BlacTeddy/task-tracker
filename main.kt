import java.io.File

data class Task(val text: String, var done: Boolean)

fun main() {
    val tasks = mutableListOf<Task>()

    // val completed = mutableListOf<String>()
    val file = File("tasks.csv")

    if(!file.exists()){
        file.createNewFile()
    }
    file.forEachLine{line ->
        val clean = line.trim()
            if (clean.isEmpty()){return@forEachLine}
        val parts = clean.split(",")
            if (parts.size != 2){return@forEachLine}
        val text = parts[0].trim()
        val done = parts[1].toBoolean()
        tasks.add(Task(text, done))
        // tasks.add(line)
    }

    while (true){
        println("=== Task Tracker ===")
        println("1. Add task")
        println("2. Edit tasks")
        println("3. View tasks")
        println("4. Save & Exit")
        print("Choose an option: ")
        val choice = readln().trim()

        when(choice){
            "1" -> addTask(tasks)
            "2" -> editTask(tasks)
            "3" -> {viewTasks(tasks)}
            "4" -> {saveFile(file, tasks); println("All Tasks Saved\n\t~Bye~"); return}
            else -> println("Invalid option")
        }
    }
}
fun confirm(followUp: String): Boolean{
    print("Are you sure you want to $followUp? (Y/N): ")
    val answer = readln().trim().lowercase()
    //  TODO IF YES OR NO \\DONE
    if(answer == "yes" || answer == "y"){
        return true
    }else{
        println("Failed to $followUp!")//.replaceFirstChar { it.uppercase()})
        return false
    }
}
fun saveFile(file: File, tasks: List<Task>) {
    file.printWriter().use { out ->
        for (task in tasks) {
            out.println("${task.text.replaceFirstChar {it.uppercase()}},${task.done}")
        }
    }
}


fun addTask(tasks: MutableList<Task>){
    print("Add a task: ")
    val text = readln().trim()
    if(confirm("add task")==true){
        tasks.add(Task(text, false))
        println("Task was added")
    }else{
        return
    }
}
fun editTask(tasks: MutableList<Task>){
    if(tasks.isEmpty()) {
        println("\t-No tasks to edit yet-")
        return
    } else {
        println("Do you want to:\n1. remove task\n2. complete task\n3. Exit")
        print("Choose an option: ")
        val choice = readln().trim()
        when(choice){
            "1" -> removeTask(tasks)
            "2" -> completeTask(tasks)
            else -> {println("Edit Task Failed"); return}
        }   
    }
}

fun viewTasks(tasks: List<Task>) {
    println("Your tasks:\n")
    if(tasks.isEmpty()) {
        println("\t-No tasks yet-")
        return
    } else {
        tasks.forEachIndexed { index, task ->
            if(task.done){
                println("${index + 1}. ${task.text}, ✓[x]")
            }else{
                println("${index + 1}. ${task.text}, ✕[]")
            }
        }
        print("\n")
    }
}
// fun viewCompeleteTasks(completed: List<String>) {
//     println("Compeleted tasks:\n")
//     if(completed.isEmpty()) {
//         println("\t-No completed tasks yet-")
//         return
//     } else {
//         completed.forEachIndexed { 
//             index, completed -> println("${index + 1}. $completed")
//         }
//         print("\n")
//     }
// }
fun removeTask(tasks: MutableList<Task>){
    viewTasks(tasks)
    print("Choose an option: ")
    val choice = readln().trim().toIntOrNull()
        if(choice == null){
            println("Invald task")
            return
        }else if(choice <= 0 || choice > tasks.size){
            println("No task there")
            return            
        }else{
            if(confirm("remove this task")==true){
                tasks.removeAt(choice-1)
                println("Task was removed")
            }
        }
}

fun completeTask(tasks: MutableList<Task>){
    viewTasks(tasks)
    print("Choose an option: ")
    val choice = readln().trim().toIntOrNull()
        if(choice == null){
            println("Invald task")
            return
        }else if(choice <= 0 || choice > tasks.size){
            println("No task to complete there")
            return            
        }else{
            if(tasks[choice-1].done == true && confirm("un-complete this task")==true){
                tasks[choice-1].done = false
            }else if(confirm("complete this task")==true){
                tasks[choice-1].done = true
                println("Task Compeleted!")
            }
        }
}

// println("coming soon")

