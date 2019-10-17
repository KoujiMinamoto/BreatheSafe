BreatheSafe
==
Introduction
--
This document is intended to serve developers of BreatheSafe application and website and
highlight the key design and development principles of these products. It will also provide an
overview of possible system improvement.
Section 2 outlines the architecture of the current system. As a system that relies on data,
sections 3 and 4 describe the details of data sources, data wrangling procedures and usages
in the database. The structure of the database is highlighted by a conceptual level ER-diagram.
Section 5 deals with the database structure. Section 6 describes the data model used in risk
determination and the ranges of the various meteorological factors. Section 7 provides some
information about the code artefacts. Section 8 describes the tools used in the product.
Section 9 provides current security measures implemented in the system and best practices
to ensure better security measures. Section 10 introduces the general maintenance
instructions and section 11 highlights some of the error situations that may be encountered
and their troubleshooting mechanisms. Section 12 suggests possible improvements of the
product and section 13 provides testing information and concludes with a discussion of all
testing methods employed in shipping this application. 


Source Code
--

This folder mainly holds the source code for the entire system and has two subfolders
corresponding to the two subcomponents of the system.
1. BreatheSafe App: This contains the source code for the app, developed using Android
Studio in Java.
2. BreatheSafely Service: This contains the source code for the Azure backend service and
wrangling scripts in two separate subfolders. The folder Source
Code/BreatheSafelyService/BreatheSafelyWeb holds the code for the service in .NET Core
2.2. The folder SourceCode/BreatheSafelyService/wrangling holds datasets and wrangling
scripts used in the application developed using Python 3 and Jupyter notebooks.
