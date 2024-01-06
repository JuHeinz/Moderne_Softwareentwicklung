from fastapi import FastAPI
import math

# SET UP API
# create api object
app = FastAPI()


# create endpoint
@app.get("/get-route/")
# function that runs when route is accessed
def get_route(latA: float, longA: float, latB: float, longB: float, ):  # declare what data type fastAPI should expect
    direction = calculate_direction(latA, longA, latB, longB)
    distance = calculate_distance(latA, longA, latB, longB)

    return {"direction": direction,
            "distance": distance}


@app.get("/get-distance/")
def get_distance(latA: float, longA: float, latB: float, longB: float, ):
    distance = calculate_distance(latA, longA, latB, longB)
    return distance


# SERVICE
# calculate distance between two coordinates using the haversine formula

def calculate_distance(latitude_a, longitude_a, latitude_b, longitude_b):
    # Convert latitude and longitude from degrees to radians
    latitude_a_radian = math.radians(latitude_a)
    longitude_a_radian = math.radians(longitude_a)
    latitude_b_radian = math.radians(latitude_b)
    longitude_b_radian = math.radians(longitude_b)

    # Calculate the differences
    delta_latitude = latitude_b_radian - latitude_a_radian
    delta_longitude = longitude_b_radian - longitude_a_radian

    # Haversine formula
    a = math.sin(delta_latitude / 2) ** 2 + math.cos(latitude_a_radian) * math.cos(latitude_b_radian) * math.sin(
        delta_longitude / 2) ** 2
    c = 2 * math.atan2(math.sqrt(a), math.sqrt(1 - a))

    # earth radius
    R = 6371.0

    # Calculate the distance
    distance = R * c

    return distance


# calculate direction on compass from coordinate A to B
def calculate_direction(latitude_a, longitude_a, latitude_b, longitude_b):
    # Convert latitude and longitude from degrees to radians
    lat1_rad = math.radians(latitude_a)
    lon1_rad = math.radians(longitude_a)
    lat2_rad = math.radians(latitude_b)
    lon2_rad = math.radians(longitude_b)

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
