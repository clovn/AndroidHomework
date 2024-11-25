package com.example.viewpager

object DataRepository {
    val questions = listOf(
        Question(
            id = 0,
            text = "What is your favorite programming language?",
            answers = listOf(
                "Kotlin",
                "Java",
                "Python",
                "JavaScript",
                "C++",
                "Other"
            )
        ),
        Question(
            id = 1,
            text = "Which framework do you prefer for web development?",
            answers = listOf(
                "Spring Boot",
                "Django",
                "React",
                "Angular",
                "Vue.js",
                "Other"
            )
        ),
        Question(
            id = 2,
            text = "What is the best way to learn programming?",
            answers = listOf(
                "Reading books",
                "Watching tutorials",
                "Taking online courses",
                "Practicing with projects",
                "Pair programming",
                "Joining a bootcamp"
            )
        ),
        Question(
            id = 3,
            text = "Which type of applications do you enjoy developing?",
            answers = listOf(
                "Mobile apps",
                "Web apps",
                "Games",
                "Desktop software",
                "APIs",
                "Other"
            )
        ),
        Question(
            id = 4,
            text = "What motivates you to code?",
            answers = listOf(
                "Problem-solving",
                "Creativity",
                "Career opportunities",
                "Learning new things",
                "Community contributions",
                "Other"
            )
        ),
        Question(
            id = 5,
            text = "What is your favorite development environment?",
            answers = listOf(
                "IntelliJ IDEA",
                "VS Code",
                "Eclipse",
                "PyCharm",
                "Android Studio",
                "Other"
            )
        ),
        Question(
            id = 6,
            text = "Which operating system do you prefer for development?",
            answers = listOf(
                "Windows",
                "macOS",
                "Linux",
                "ChromeOS",
                "Other"
            )
        ),
        Question(
            id = 7,
            text = "What is your preferred database management system?",
            answers = listOf(
                "MySQL",
                "PostgreSQL",
                "MongoDB",
                "SQLite",
                "Oracle DB",
                "Other"
            )
        ),
        Question(
            id = 8,
            text = "Which version control system do you use most often?",
            answers = listOf(
                "Git",
                "Subversion",
                "Mercurial",
                "TFS",
                "Other"
            )
        ),
        Question(
            id = 9,
            text = "How do you debug code most effectively?",
            answers = listOf(
                "Using a debugger",
                "Adding print statements",
                "Using logs",
                "Pair debugging",
                "Reading documentation",
                "Other"
            )
        ),
        Question(
            id = 10,
            text = "What is the most important factor in choosing a tech stack?",
            answers = listOf(
                "Performance",
                "Ease of learning",
                "Community support",
                "Scalability",
                "Cost",
                "Other"
            )
        ),
        Question(
            id = 11,
            text = "What is your favorite programming paradigm?",
            answers = listOf(
                "Object-Oriented Programming",
                "Functional Programming",
                "Procedural Programming",
                "Event-Driven Programming",
                "Declarative Programming",
                "Other"
            )
        )
    )

    fun getQuestion(id : Int) = questions.find { it.id == id }
}