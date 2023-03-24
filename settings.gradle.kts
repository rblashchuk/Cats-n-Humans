rootProject.name = "rblashchuk"
include("lab-2")
include("lab-2:DAO")
findProject(":lab-2:DAO")?.name = "DAO"
include("lab-2:DAO:sessions")
findProject(":lab-2:DAO:sessions")?.name = "sessions"
include("lab-2:Service")
findProject(":lab-2:Service")?.name = "Service"
