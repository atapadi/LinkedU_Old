ALTER TABLE USERS ALTER COLUMN FIRSTNAME SET DATA TYPE VARCHAR(50);
ALTER TABLE USERS ALTER COLUMN LASTNAME SET DATA TYPE VARCHAR(50);

INSERT INTO USERS
      VALUES ('isu', 'isu', 'Illinois State University', 'Illinois State University', 'EMAIL', '', '', 'University', null);

INSERT INTO USERS
      VALUES ('uic', 'uic', 'University Of Illinois Chicago', 'University Of Illinois at Chicago', 'EMAIL', '', '', 'University', null);


INSERT INTO USERS
      VALUES ('uiuc', 'uiuc', 'University Of Illinois Urbana Champaign', 'University Of Illinois Urbana Champaign', 'EMAIL', '', '', 'University', null);


INSERT INTO USERS
      VALUES ('harvard', 'harvard', 'Harvard', 'Harvard University', 'EMAIL', '', '', 'University', null);

INSERT INTO USERS
      VALUES ('princeton', 'princeton', 'Princeton', 'Princeton University', 'EMAIL', '', '', 'University', null);