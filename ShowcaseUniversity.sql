CREATE TABLE ShowcaseUniversity(
	ShowcaseID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
	UniversityName VARCHAR(100),
    ImageName VARCHAR(50),
    IsShowCase CHAR(1),
    CONSTRAINT showcaseUniversity_showcaseID_pk PRIMARY KEY (ShowcaseID));
	
	
	INSERT into ShowcaseUniversity (UniversityName,ImageName,IsShowCase) VALUES ('Harvard University','harvard.png','1');
	INSERT into ShowcaseUniversity (UniversityName,ImageName,IsShowCase) VALUES ('Princeton University','princeton.png','1');
	INSERT into ShowcaseUniversity (UniversityName,ImageName,IsShowCase) VALUES ('Illinois State University','ISU1.png','0');
	INSERT into ShowcaseUniversity (UniversityName,ImageName,IsShowCase) VALUES ('University of Illinois Chicago','UIC.png','0');
	INSERT into ShowcaseUniversity (UniversityName,ImageName,IsShowCase) VALUES ('University of Illinois at Urbana-Champaign','UIUC.jpg','0');
	INSERT into ShowcaseUniversity (UniversityName,ImageName,IsShowCase) VALUES ('Yale University','yale.jpg','0');
	INSERT into ShowcaseUniversity (UniversityName,ImageName,IsShowCase) VALUES ('University of Chicago','universitychicago.jpg','0');
	INSERT into ShowcaseUniversity (UniversityName,ImageName,IsShowCase) VALUES ('Columbia University','columbia.jpg','0');
	INSERT into ShowcaseUniversity (UniversityName,ImageName,IsShowCase) VALUES ('Stanford University','stanford.png','0');
	INSERT into ShowcaseUniversity (UniversityName,ImageName,IsShowCase) VALUES ('Duke University','duke.jpg','0');