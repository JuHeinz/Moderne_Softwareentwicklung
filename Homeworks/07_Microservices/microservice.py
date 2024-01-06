from fastapi import FastAPI
import math

# SET UP API
#create api object
app = FastAPI()

#create endpoint
@app.get("/get-route/")
 #function that runs when route is accessed
def get_route(latA: float, longA: float, latB: float, longB: float, ): #declare what data type fastAPI should expect
    direction = calculateDirection(latA, longA, latB, longB)
    distance = calculateDistance(latA, longA, latB, longB)                         

    return {"direction": direction,
            "distance": distance}


    
# SERVICE
#calculate distance between two coordinates using the haversine formula
def calculateDistance(latitudeA, longitudeA, latitudeB, longitudeB):
    # Convert latitude and longitude from degrees to radians
    latitudeA_radian = math.radians(latitudeA)
    longitudeA_radian = math.radians(longitudeA)
    latitudeB_radian = math.radians(latitudeB)
    longitudeB_radian = math.radians(longitudeB)

    # Calculate the differences
    deltaLatitude = latitudeB_radian - latitudeA_radian
    deltaLongitude = longitudeB_radian - longitudeA_radian

    # Haversine formula
    a = math.sin(deltaLatitude / 2)**2 + math.cos(latitudeA_radian) * math.cos(latitudeB_radian) * math.sin(deltaLongitude / 2)**2
    c = 2 * math.atan2(math.sqrt(a), math.sqrt(1 - a))
    
    # earth radius
    R = 6371.0

    # Calculate the distance
    distance = R * c

    return distance

# calculate direction on compass from coordinate A to B    
def calculateDirection(latitudeA, longitudeA, latitudeB, longitudeB):
    # Convert latitude and longitude from degrees to radians
    lat1_rad = math.radians(latitudeA)
    lon1_rad = math.radians(longitudeA)
    lat2_rad = math.radians(latitudeB)
    lon2_rad = math.radians(longitudeB)

    # Calculate differences in longitude and latitude
    dlon = lon2_rad - lon1_rad

    # Calculate direction
    y = math.sin(dlon) * math.cos(lat2_rad)
    x = math.cos(lat1_rad) * math.sin(lat2_rad) - math.sin(lat1_rad) * math.cos(lat2_rad) * math.cos(dlon)
    direction = math.atan2(y, x)

    # Convert direction from radians to degrees and normalize to the range [0, 360)
    direction = math.degrees(direction)
    direction = (direction + 360) % 360

    return direction

