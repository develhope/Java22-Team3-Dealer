package com.develhope.spring.features.errors;

public class VehicleError {

    public static class VehicleNotFound extends GenericErrors {
        public VehicleNotFound() {
            super(433,"No vehicles found");
        }
    }

    public static class VehicleIdNotFoundExc extends GenericErrors {
        public VehicleIdNotFoundExc(Long vehicleId, Exception e) {
            super(434, "No vehicle found for the id "+ vehicleId+ ". Caught exception: " + e.getMessage());
        }
    }
    public static class VehicleEmpty extends GenericErrors {
        public VehicleEmpty(Long vehicleId) {
            super(435, "The vehicle with the id: " + vehicleId + " is empty");
        }
    }
}
