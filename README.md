# Scheduler
A working model of my universities registration system.

How it works:
This program models a university registration system. When the user starts it up they will be presented with a login screen where they
can login as either a registrar to organize registration or a student to regester for courses. Starting with the registrar, the initail 
login ID and password are both "he". These can be set to any value in the registrar.properties file. Once logged in as the registrar the
user can then load in from file or manually add students, faculty memebers, and courses from each of their respective panels, accessible
from the top menu bar. I reccomend loading in the students from "test-files/student_records.txt", faculty from "test-files/faculty_records
.txt", and courses from "test-flies/course_records.txt" to get a general idea of how the system works. From there the registrar can manually manipulate
the lists of students, faculty, and courses; save them to CSV files; or assign which faculty will be teaching which course. To see the student 
side of the project I reccomend adding a student to the student directory, making note of their ID and password. Click the logout button and 
relogin with the new student's ID and password. From here the student can select courses loaded in by the registrar to add into their schedule, view
their details, and save their final schedule to a CSV file.

How to run it:
Download the project into and IDE and then navigate to PackSchedulerGUI file in src/edu/ncsu/csc216/pack_scheduler/ui directory and run it.
