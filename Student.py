class Student:

    def __init__(FirstName, LastName, GPA, Major, FacultyAdvisor):
        FirstName = str(FirstName)
        LastName = str(LastName)
        GPA = float(GPA)
        Major = str(Major)
        FacultyAdvisor = str(FacultyAdvisor)

    ### Accessors

    def getVals():
        return FirstName, LastName, GPA, Major, FacultyAdvisor

    def getFirstName():
        return FirstName

    def getLastName(self):
        return LastName

    def getGPA():
        return GPA

    def getMajor():
        return Major

    def getFacultyAdvisor():
        return FacultyAdvisor

    ### Mutators

    def setFirstName(name):
        FirstName = str(name)

    def setLastName(name):
        LastName = str(name)

    def setGPA(GPA):
        GPA = float(GPA)

    def setMajor(Major):
        Major = str(Major)

    def setFacultyAdvisor(Advisor):
        FacultyAdvisor = str(Advisor)