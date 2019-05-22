import sqlite3
from sqlite3 import Error

def create_connection():
    try:
        connection = sqlite3.connect('fitnessfreak.db')
        return connection
    except Error:
        print(Error)

def create_bmi_table(connection):
    print("A")
    cursor_obj = connection.cursor()
    cursor_obj.execute('''
        CREATE TABLE BMI(
            id integer PRIMARY KEY,
            name text,
            lower real,
            upper real
        )
    ''')
    print("B")
    connection.commit()

def create_nutri_table(connection):
    print("C")
    cursor_obj = connection.cursor()
    cursor_obj.execute('''
        CREATE TABLE nutriplan(
            nutri_id text PRIMARY KEY,
            b_id integer,
            FOREIGN KEY(b_id) REFERENCES BMI(id)
        )
    ''')
    print("D")
    connection.commit()

def create_exercise_table(connection):
    cursor_obj = connection.cursor()
    print("E")
    cursor_obj.execute('''
        CREATE TABLE exercise(
            exercise_id text PRIMARY KEY,           
            ex_name text,
            bmi_id integer,
            FOREIGN KEY(bmi_id) REFERENCES BMI(id)
        )
    ''')
    print("F")
    connection.commit()

def create_food_table(connection):
    print("G")
    cursor_obj = connection.cursor()
    cursor_obj.execute('''
        CREATE TABLE fooditem(
            food_id text PRIMARY KEY,
            fooditem text,
            additionalinfo text,
            limiteduse integer,
            n_id text,
            FOREIGN KEY(n_id) REFERENCES nutriplan(nutri_id)
        )
    ''')
    print("H")
    connection.commit()

def insert_values(connection):
    print("I")
    cursor_object = connection.cursor()
    cursor_object.execute('''
        INSERT INTO BMI values 
        (1, "Underweight", 0, 18.4),
        (2, "Normal", 18.5, 24.9 ),
        (3, "Overweight", 25, 800)
    ''')
    cursor_object.execute('''
        INSERT INTO exercise values 
        ("E1", "Push-ups", 1),
        ("E2", "Low Intensity Aerobic Workout",  1),
        ("E3", "Swimming", 1),
        ("E4", "Jogging", 1),
        ("E5", "Yoga", 1),
        ("E6", "Walking", 2),
        ("E7", "Running", 2),
        ("E8", "Cycling", 2),
        ("E9", "Planks", 3),
        ("E10", "Jumping Jacks", 3),
        ("E11", "Skipping", 3),
        ("E12", "Hip Twists", 3),
        ("E13", "Squat Jumps", 3),
        ("E14", "Bicycle Crunches", 3),
        ("E15", "Knee High", 3),
        ("E16", "Moderate Intensity Walking", 3)
    ''')
    print("J")
    cursor_object.execute('''
        INSERT INTO nutriplan values 
        ("N1",1),
        ("N2",2),
        ("N3",3)
    ''')
    print("K")
    cursor_object.execute('''
        INSERT INTO fooditem values 
        ("F1", "Whole Grain","Whole Wheat, Oats, Brown Rice" ,0,"N3"),
        ("F2", "Vegetables"," ",0,"N3"),
        ("F3", "Whole Fruits", " ",0,"N3"),
        ("F4", "Nuts, Seeds and Beans","Limited Fish and Poultry also" ,0,"N3"),
        ("F5", "Use Plant Oils","Olive and other vegetable oils",0,"N3"),
        ("F6", "Sugar","Avoid sweetned drinks like sports drinks,soda etc as well",1,"N3"),
        ("F7", "Fruit juices"," Only a small amount per day",1,"N3"),
        ("F8", "Refined Grains","For example white bread, white rice, white pasta ",1,"N3"),
        ("F9", "Potatoes"," ",1,"N3"),
        ("F10", "Red Meat and Processed Meats","For example,beef,pork,salami,sausage ",1,"N3"),
        ("F11", "Full Cream Milk"," ",0,"N1"),
        ("F12", "Meat, Fish and Eggs","Protein rich food",0,"N1"),
        ("F13", "Bread  and Cereals"," Upto 6 cups of starch a day",0,"N1"),
        ("F14", "Healthy Desserts","Puddings, smoothies etc",0,"N1"),
        ("F15", "Rice"," ",0,"N1"),
        ("F16", "Red Meats", "Lamb, Beef etc",0,"N1"),
        ("F17", "Full-fat Yogurt"," Avoid flavoured yogurts",0,"N1"),
        ("F18", "Cheese"," ",0,"N1"),
        ("F19", "Dark Chocolate","Select chocolate with at least 70 percent cocoa content",0,"N1"),
        ("F20", "Dried Fruits"," ",0,"N1"),
        ("F21","Detox Smoothies"," ",0,"N2"),
        ("F22","Avacados"," ",0,"N2"),
        ("F23","Beans"," ",0,"N2"),
        ("F24","Berries"," ",0,"N2"),
        ("F25","Broccoli"," ",0,"N2"),
        ("F26","Nuts and seeds"," ",0,"N2"),
        ("F27","Oranges"," ",0,"N2"),
        ("F28","Sweet Potatoes"," ",0,"N2"),
        ("F29","Eggs"," ",0,"N2"),
        ("F30","Fish"," ",0,"N2")
    ''')
    print("L")
    connection.commit()

connection = create_connection()

try:
    create_bmi_table(connection)
    create_nutri_table(connection)
    create_exercise_table(connection)
    create_food_table(connection)
except: 
    print('Oops, Database is already created!')

try:
    insert_values(connection)
except:
    print('Error inserting values :(')
