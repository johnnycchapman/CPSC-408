class Student:

    def __init__(self,FirstName, LastName, GPA, Major, FacultyAdvisor):
        self.FirstName = str(FirstName)
        self.LastName = str(LastName)
        self.GPA = float(GPA)
        self.Major = str(Major)
        self.FacultyAdvisor = str(FacultyAdvisor)

    ### Accessors

    def getVals(self):
        return (self.FirstName, self.LastName, self.GPA, self.Major, self.FacultyAdvisor,)

    def getFirstName(self):
        return self.FirstName

    def getLastName(self):
        return self.LastName

    def getGPA(self):
        return self.GPA

    def getMajor(self):
        return self.Major

    def getFacultyAdvisor(self):
        return self.FacultyAdvisor

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
