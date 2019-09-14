# :weight_lifting_man: :muscle: Fitness Kiosk API :muscle: :weight_lifting_woman:

This directory contains the API for the fitness client. It is built using the flask python library.

The API can also be hosted. For this project, this API was hosted on [heroku](https://www.heroku.com).

Sample http get request - http://127.0.0.1:5000/v1/resources/nutrition/bmi?name=Underweight

Query Parameters 

1. Underweight 
1. Overweight
1. Normal

:warning: You can also get a list of Workouts from the API.

You can find more information by examining the [app.py](app.py) and [create_fitnessdatabase.py](create_fitnessdatabase.py) files.

The ```app.py``` file contains the code for the http requests and the ```create_fitnessdatabase.py``` file contains the code that creates the necessary tables for the API.