DROP TABLE dememerr.employee;
CREATE TABLE dememerr.employee (
  `EmployeeId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `UserName` varchar(200) NOT NULL,
  `Password` varchar(200) NOT NULL,
  `FirstName` varchar(200) NOT NULL,
  `LastName` varchar(200) NOT NULL,
  `Email` varchar(200) NOT NULL,
  PRIMARY KEY (`EmployeeId`),
  UNIQUE KEY `EmployeeId_UNIQUE` (`EmployeeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE dememerr.users (
  `UserId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `UserName` varchar(200) NOT NULL,
  `FirstName` varchar(200) NOT NULL,
  `LastName` varchar(200) NOT NULL,
  `Email` varchar(200) NOT NULL,
  `Address` varchar(200) DEFAULT NULL,
  `Phone` varchar(15) DEFAULT NULL,
  `Password` varchar(200) NOT NULL,
  PRIMARY KEY (`UserId`),
  UNIQUE KEY `UserId_UNIQUE` (`UserId`),
  UNIQUE KEY `Username_UNIQUE` (`UserName`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE dememerr.friendslist (
  `FriendsListId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `UserId` int(11) NOT NULL,
  `FriendId` int(11) NOT NULL,
  PRIMARY KEY (`FriendsListId`),
  UNIQUE KEY `FriendsListId_UNIQUE` (`FriendsListId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE dememerr.posts;
CREATE TABLE dememerr.posts (
  `PostId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `UserId` int(11) NOT NULL,
  `ImageLocation` varchar(8000) NOT NULL,
  `NumberOfLikes` int(10) unsigned NOT NULL,
  `NumberOfDislikes` int(10) unsigned NOT NULL,
  `PostedOn` datetime NOT NULL,
  `ParentPostId` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`PostId`),
  UNIQUE KEY `PostId_UNIQUE` (`PostId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin7;

CREATE TABLE dememerr.posts (
  PostId int(10) unsigned NOT NULL AUTO_INCREMENT,
  UserId int(11) NOT NULL,
  ImageLocation varchar(8000) NOT NULL,
  NumberOfLikes int(10) unsigned NOT NULL,
  NumberOfDislikes int(10) unsigned NOT NULL,
  PostedOn timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  ParentPostId int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (PostId),
  UNIQUE KEY PostId_UNIQUE (PostId)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin7;
