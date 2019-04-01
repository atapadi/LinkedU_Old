---------------------------------------------------------------------------------------------------

-- Break all table constraints.

---------------------------------------------------------------------------------------------------

ALTER TABLE student 
    DROP CONSTRAINT student_username_fk;

ALTER TABLE recruiter 
    DROP CONSTRAINT recruiter_university_fk;

ALTER TABLE recruiter 
    DROP CONSTRAINT recruiter_username_fk;

ALTER TABLE universityBridge
    DROP CONSTRAINT universityBridge_profileId_fk;

ALTER TABLE universityBridge
    DROP CONSTRAINT universityBridge_username_fk;

ALTER TABLE majorBridge
    DROP CONSTRAINT majorList_major_fk;

ALTER TABLE majorBridge
    DROP CONSTRAINT majorList_username_fk;

ALTER TABLE posts
    DROP CONSTRAINT posts_imageId_fk;

ALTER TABLE posts
    DROP CONSTRAINT posts_videoId_fk;

ALTER TABLE posts
    DROP CONSTRAINT posts_username_fk;

ALTER TABLE commentList
    DROP CONSTRAINT commentList_commentId_fk;

ALTER TABLE commentList
    DROP CONSTRAINT commentList_postId_fk;

---------------------------------------------------------------------------------------------------

-- If database has been established drop tables.

---------------------------------------------------------------------------------------------------

DROP TABLE users;
DROP TABLE profiles;
DROP TABLE university;
DROP TABLE universityList;
DROP TABLE major;
DROP TABLE majorList;
DROP TABLE posts;
DROP TABLE images;
DROP TABLE videos;
DROP TABLE comments;
DROP TABLE commentList;

---------------------------------------------------------------------------------------------------

--  Table Creation with CONSTRAINTS

---------------------------------------------------------------------------------------------------

CREATE TABLE users(
    userName VARCHAR(20),
    password VARCHAR(20) NOT NULL,
    firstName VARCHAR(20) NOT NULL,
    lastName VARCHAR(20) NOT NULL,
    email VARCHAR(30),
    securityQuestion VARCHAR(100),
    securityAnswer VARCHAR(100),
    userType VARCHAR(15) NOT NULL,
    CONSTRAINT users_userName_pk PRIMARY KEY (userName));

ALTER TABLE USERS
    ADD COLUMN PROFILEPICTUREID INTEGER;
ALTER TABLE USERS
    ADD CONSTRAINT profile_pic FOREIGN KEY (PROFILEPICTUREID)
        REFERENCES IMAGES(IMAGEID);

CREATE TABLE student(
    profileId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
    dateOfBirth VARCHAR(50),
    height VARCHAR(4),
    weight VARCHAR(4),
    address VARCHAR(500),
    country VARCHAR(20),
    zipcode VARCHAR(20),
    phone VARCHAR(13),
    school VARCHAR(20),
    endYear VARCHAR(4),
    sat VARCHAR(4),
    act VARCHAR(4),
    psat VARCHAR(4),
    certification VARCHAR(500),
    essay VARCHAR(1200),
    userName VARCHAR(20),
    hobbies VARCHAR(500),
    CONSTRAINT student_profileId_pk PRIMARY KEY (profileId),
    CONSTRAINT student_username_fk  FOREIGN KEY (userName)
        REFERENCES users(username));

CREATE TABLE university(
    profileId VARCHAR(50),
    universityEmail VARCHAR(50),
    username VARCHAR(20),
    CONSTRAINT university_profileId_pk PRIMARY KEY (profileId),
    CONSTRAINT university_username_fk FOREIGN KEY (username)
        REFERENCES users(username));

CREATE TABLE universityschedule(
    universityname VARCHAR(100),
    date VARCHAR(100));
		
		
CREATE TABLE universityBridge(
    bridgeId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    profileId VARCHAR(50),
    username VARCHAR(20),
    CONSTRAINT universityBridge_bridgeId_pk PRIMARY KEY (bridgeId),
    CONSTRAINT universityBridge_profileId_fk FOREIGN KEY (profileId)
        REFERENCES university(profileId),
    CONSTRAINT universityBridge_username_fk FOREIGN KEY (username)
        REFERENCES users(username));

CREATE TABLE recruiter(
    profileId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
    university VARCHAR(50),
    username VARCHAR(20),
    department VARCHAR(20),
    phone VARCHAR(13),
    CONSTRAINT recruiter_profileId_pk PRIMARY KEY (profileId),
    CONSTRAINT recruiter_username_fk FOREIGN KEY (username)
        REFERENCES users(username),
    CONSTRAINT recruiter_university_fk FOREIGN KEY (university)
        REFERENCES university(profileId));

-- CREATE TABLE hobbies (
--     hobbyId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
--     hobbieName VARCHAR(20),
--     hobbieDesc VARCHAR(20),
--     CONSTRAINT hobbies_hobbieId_pk PRIMARY KEY (hobbyId));

-- CREATE TABLE hobbiesList (
--     listId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
--     hobbyId INTEGER,
--     profileId INTEGER,
--     CONSTRAINT hobbiesList_listId_pk PRIMARY KEY (listId),
--     CONSTRAINT hobbiesList_hobbyId_fk FOREIGN KEY (hobbyId)
--         REFERENCES hobbies(hobbyId) ON DELETE CASCADE,
--     CONSTRAINT hobbieList_profileId_fk FOREIGN KEY (profileId)
--         REFERENCES profiles(profileId) ON DELETE CASCADE);


CREATE TABLE major(
    major VARCHAR(20),
    majorDesc VARCHAR(100),
    CONSTRAINT major_major_pk PRIMARY KEY (major));

CREATE TABLE majorBridge(
    bridgeId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    major VARCHAR(20),
    username VARCHAR(20),
    CONSTRAINT majorList_bridgeId_pk PRIMARY KEY (bridgeId),
    CONSTRAINT majorList_major_fk FOREIGN KEY (major)
        REFERENCES major(major),
    CONSTRAINT majorList_username_fk FOREIGN KEY (username)
        REFERENCES users(username));

CREATE TABLE images(
    imageId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    image BLOB,
    USERNAME VARCHAR(20),
    CONSTRAINT images_imageId_pk PRIMARY KEY (imageId))
/*  
  CONSTRAINT IMAGES_USERNAME_FK FOREIGN KEY (USERNAME)
        REFERENCES USERS(USERNAME); 
ALTER TABLE IMAGES ADD COLUMN IMAGE BLOB;
ALTER TABLE IMAGES ADD COLUMN USERNAME VARCHAR(20);
ALTER TABLE IMAGES ADD CONSTRAINT IMAGES_USERNAME_FK FOREIGN KEY (USERNAME) REFERENCES USERS (username);
ALTER TABLE IMAGES DROP SOURCE;
*/
CREATE TABLE videos(
    videoId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    postId INTEGER,
    CONSTRAINT videos_videoId_pk PRIMARY KEY (videoId));

ALTER TABLE VIDEOS DROP SOURCE;

CREATE TABLE posts(
    postId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    username VARCHAR(20),
    imageId INTEGER,
    videoId INTEGER,
    datesTime TIMESTAMP default CURRENT_TIMESTAMP,
    CONSTRAINT posts_postId_pk PRIMARY KEY (postId),
    CONSTRAINT posts_username_fk FOREIGN KEY (username)
        REFERENCES users(username),
    CONSTRAINT posts_imageId_fk FOREIGN KEY (imageId)
        REFERENCES images(imageId),
    CONSTRAINT posts_videoId_fk FOREIGN KEY (videoId)
        REFERENCES videos(videoId));
ALTER TABLE posts
    ADD datesTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

CREATE TABLE comments(
    commentId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    content VARCHAR(250),
    CONSTRAINT comments_commentId_pk PRIMARY KEY (commentId));

ALTER TABLE comments
    ADD datesTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

CREATE TABLE commentList(
    listId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    commentId INTEGER,
    postId INTEGER,
    CONSTRAINT commentList_listId_pk PRIMARY KEY (listId),
    CONSTRAINT commentList_commentId_fk FOREIGN KEY (commentId)
        REFERENCES comments(commentId),
    CONSTRAINT commentList_postId_fk FOREIGN KEY (postId)
        REFERENCES posts(postId));


CREATE TABLE imageBridge(
     bridgeId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
     postId INTEGER,
     imageId INTEGER,
     CONSTRAINT imageBridge_bridgeId_pk PRIMARY KEY (bridgeId),
     CONSTRAINT imageBridge_postId_fk  FOREIGN KEY (postId)
        REFERENCES posts(postId),
    CONSTRAINT imageBridge_imageId_fk FOREIGN KEY (imageId)
        REFERENCES images(imageId));

CREATE TABLE videoBridge(
    bridgeId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
     postId INTEGER,
     videoId INTEGER,
     CONSTRAINT videoBridge_bridgeId_pk PRIMARY KEY (bridgeId),
     CONSTRAINT videoBridge_postId_fk  FOREIGN KEY (postId)
        REFERENCES posts(postId),
    CONSTRAINT videoBridge_videoId_fk FOREIGN KEY (videoId)
        REFERENCES videos(videoId));
