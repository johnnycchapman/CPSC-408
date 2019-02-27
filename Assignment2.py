import sqlite3

from Student import Student;

if __name__ == "__main__":
    # create sqlite connection
    conn = sqlite3.connect("StudentDB.db")
    cursor = conn.cursor()
    print("Connect to sqlite3")


    def __init__(self, conn, cursor):
        self.conn = conn
        self.cursor = cursor

    # create students table
    cursor.execute("CREATE TABLE IF NOT EXISTS Students ("
                    "StudentId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                    "FirstName VARCHAR(25) NOT NULL,"
                    "LastName VARCHAR(25) NOT NULL,"
                    "GPA REAL NOT NULL,"
                    "Major VARCHAR(10) NOT NULL,"
                    "FacultyAdvisor VARCHAR(25)"
                    ");")
    conn.commit()

    print()
    print("Menu:")
    print("1. Display Students\n"
          "2. Add Student\n"
          "3. Update Student\n"
          "4. Delete Student\n"
          "5. Search Student\n"
          "6. Quit")
    while True:
        try:
            option = int(input("Enter Option: \n"))
            if option > 6 or option < 1:
                raise Exception()
            break
        except Exception:
            print("Invalid Input!")

    while True:
        if option == 2:
            print()
            # Loop until user confirms correct data
            isCorrect = False
            while not isCorrect:
                # Get data as input
                FirstName = input("Enter First Name: \n")
                LastName = input("Enter Last Name: \n")
                GPA = input("Enter GPA: \n")
                Major = input("Enter Major: \n")
                FacultyAdvisor = input("Enter Faculty Advisor: ")

                # Ask user if data is correct
                print()
                print("First Name:", FirstName, "\nLast Name:", LastName,
                      "\nGPA:", GPA, "\nMajor:", Major, "\nFaculty Advisor:", FacultyAdvisor)
                print()
                while True:
                    valid = input("Are these values correct? ('Y' / 'N') ")
                    if valid.upper() == "Y":
                        isCorrect = True
                        break
                    elif valid.upper() == "N":
                        print()
                        print("Please reenter values!")
                        break
                    else:
                        print("Invalid option!")

            # Write to database
            student = Student(FirstName, LastName, GPA, Major, FacultyAdvisor)
            cursor.execute("INSERT INTO Students(FirstName, LastName, GPA, Major, FacultyAdvisor) "
                                "VALUES(?,?,?,?,?);", student.getVals())
            conn.commit()
            print("Student entered successfully!")






