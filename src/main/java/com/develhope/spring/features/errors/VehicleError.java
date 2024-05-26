package com.develhope.spring.features.errors;

public class VehicleError {

    public static class VehicleNotFound extends GenericErrors {
        public VehicleNotFound() {
            super(433, "No vehicles found");
        }
    }

    public static class VehicleIdNotFoundExc extends GenericErrors {
        public VehicleIdNotFoundExc(Long vehicleId) {
            super(434, "No vehicle found for the id " + vehicleId);
        }
    }

    public static class VehicleListEmpty extends GenericErrors {
        public VehicleListEmpty() {
            super(435, "Your list of vehicleEntities is empty");
        }
    }

    public static class ImpossibleToCreateVehicle extends GenericErrors {
        public ImpossibleToCreateVehicle() {
            super(436, "Impossible to create a new vehicle");
        }
    }

    public static class UnexpectedErrorOccurred extends GenericErrors {
        public UnexpectedErrorOccurred(Exception e) {
            super(435,"An unexpected error occurred: " + e.getMessage());
        }
    }
}
