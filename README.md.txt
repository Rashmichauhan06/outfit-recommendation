AI-Powered Outfit Recommendation System
a. Project Overview

This project is a Full-Stack AI-Powered Outfit Recommendation System that generates complete outfit combinations using a single base product as input.

The system simulates the reasoning of a fashion stylist, considering:

1. Style compatibility

2. Occasion appropriateness

3. Seasonal relevance

4. Color harmony

5. Budget constraints

The goal of the project is not UI polish, but system design clarity, recommendation logic quality, and performance awareness.

b. Architecture Overview

> High-Level Architecture

Angular Frontend
     |
     |  REST API (JSON)
     v
Spring Boot Backend
     |
     |  JPA / Hibernate
     v
MySQL Database

> Project Structure

outfit-recommendation-system/
│
├── README.md
│
├── backend/                  → Spring Boot REST API
│   ├── controller/
│   ├── service/
│   ├── engine/
│   ├── model/
│   ├── repository/
│   └── config/
│
└── outfit-ui/                → Angular Frontend
    ├── components/
    ├── services/
    └── pages/

c. Recommendation Logic

1️⃣ Input

Base Product ID
Maximum Budget

2️⃣ Outfit Generation

Each outfit always contains:

1 Top
1 Bottom
1 Footwear
1 Accessory

The base product can belong to any category and is reused appropriately.

3️⃣ Scoring Mechanism

Outfits are scored using a rule-based AI scoring engine:

Factor	Score
Style match	+0.3
Occasion match	+0.3
Season match / ALL	+0.2
Color compatibility	+0.2
Max Score	1.0

Color Compatibility Logic
BLACK and WHITE are treated as neutral colors

4️⃣ Budget Handling

Total outfit price is calculated.
Each outfit is marked:
withinBudget = true / false

Budget-compliant outfits are prioritized, but stylistically strong outfits are still returned if none fit the budget

5️⃣ Ranking Strategy

Outfits are sorted by:

Within budget first
Higher match score
Lower total price

Top 5 outfits are returned.

d. Performance Strategy (Sub-1 Second)

To meet the < 1 second response time requirement, the system uses:

✅ In-Memory Caching
Products are preloaded into a ProductCache
Grouped by category for O(1) access

✅ Lightweight AI
Rule-based scoring (no heavy ML inference)
Pure in-memory computation

✅ Minimal Database Calls

DB hit only for:
Base product lookup
Initial cache load

✅ No Blocking IO

No external APIs
No file system access
Typical API response time: ~50–200 ms

e. AI Usage

This project uses a rule-based AI recommendation engine, which is:
Explainable
Fast
Deterministic
Suitable for real-time systems

No external AI/ML APIs were used intentionally to:

Maintain sub-1s performance
Keep logic transparent and debuggable

🔌 API Endpoints
1️⃣ Get All Products
GET /api/products

Response

[
  {
    "id": 1,
    "name": "Black Cargo Pants",
    "category": "BOTTOM",
    "price": 2499
  }
]

2️⃣ Get Outfit Recommendations
POST /api/recommendations


Request

{
  "baseProductId": 1,
  "maxBudget": 30000
}


Response

[
  {
    "items": [...],
    "matchScore": 0.85,
    "withinBudget": true,
    "totalPrice": 28790
  }
]

f. How to Run Locally
Backend (Spring Boot)
cd backend
mvn spring-boot:run


Runs on:

http://localhost:8089

Frontend (Angular)
cd outfit-ui
npm install
ng serve


Runs on:

http://localhost:4200

📦 Sample Data

Sample products are loaded using a DataLoader

Covers:

Multiple categories
Multiple styles (Casual, Sport, Ethnic, Party)
Multiple occasions (Daily, Office, Party, Festive)

g. Assumptions & Trade-offs
Assumptions

Limited product dataset
Static attributes (no user behavior tracking)
Rule-based AI is sufficient for demo

Trade-offs

No deep learning model (for speed & clarity)
No user personalization
No image analysis

🚀 Future Improvements

User preference learning
Collaborative filtering
Seasonal trend modeling
Async background recommendation pre-computation
Image-based similarity matching

(Author: Rashmi Chauhan)