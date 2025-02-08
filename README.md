# Car Insurance Calculator with MySQL & JavaFX

## **Overview**
This is a **JavaFX-based Car Insurance Calculator** that allows users to **calculate insurance premiums**, register and log in, and store insurance data in a **MySQL database**. The system also includes an **admin panel** with data visualization using pie and line charts.

## **Features**

### **1. User Registration & Login**
- Users can **register** with their **name, date of birth, salary, gender, and status**.
- Login authentication is handled via **MySQL**.
- Users can **store their insurance data** in the database.

### **2. Insurance Calculation**
- Users select:
  - **Car Type** (Sedan, SUV, Hatchback, Coupe)
  - **Coverage Type** (Comprehensive, Third Party)
  - **Engine Capacity (cc)**
  - **Car Price (RM)**
  - **Location** (Malaysia, India, Japan, etc.)
  - **No Claims Discount (NCD)**
- The system calculates the estimated **yearly insurance premium** based on:
  - **Base insurance rate (3.1% of car price)**
  - **Discounts based on NCD**
  - **Modifiers based on engine capacity**

### **3. Admin Dashboard**
- Admin users can:
  - **Search users** and view their insurance records.
  - **Generate pie charts** for car type distribution.
  - **Generate line charts** for location-based insurance interest.

### **4. Database Integration (MySQL)**
- User details and insurance data are **stored in a MySQL database**.
- Uses **JDBC for database connectivity**.

### **5. Data Visualization**
- **Pie charts** display car type distribution.
- **Line charts** show location-based interest in insurance.

---
