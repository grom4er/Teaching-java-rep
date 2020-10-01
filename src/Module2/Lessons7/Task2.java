package Module2.Lessons7;

interface garageFunctionMiniGarage {
    boolean enterGarage(Car car);

    boolean leaveGarage();

    default void vehicleEnterText(Vehicle vehicle) {
        System.out.println(vehicle.getClass().getSimpleName() + " is parking");
    }

    default void vehicleLeaveText(Vehicle vehicle) {
        System.out.println(vehicle.getClass().getSimpleName() + " is out of garage");
    }


}

interface garageFunctionGarage extends garageFunctionMiniGarage {
    boolean enterGarage(Truck truck);

    boolean leaveGarage();
}

interface garageFunctionBigGarage {
    boolean enterGarage(Vehicle vehicleFirst, Vehicle vehicleSecond);

    boolean enterGarage(Vehicle vehicle);

    boolean leaveGarage(Vehicle vehicle);

    boolean leaveGarage(Vehicle vehicleFirst, Vehicle vehicleSecond);

    default void vehicleEnterText(Vehicle vehicleOne, Vehicle vehicleTwo) {
        System.out.println(vehicleOne.getClass().getSimpleName()
                + " and  "
                + vehicleTwo.getClass().getSimpleName()
                + " is parking");
    }

    default void vehicleLeaveText(String nameVehicleOne, String nameVehicleTwo) {
        System.out.println(nameVehicleOne + " and " + nameVehicleTwo + " is out of garage");
    }

    default void vehicleEnterText(Vehicle vehicle) {
        System.out.println(vehicle.getClass().getSimpleName() + " is parking");
    }

    default void vehicleLeaveText(String name) {
        System.out.println(name + " is out of garage");
    }
}


class MiniGarage implements garageFunctionMiniGarage {
    private Vehicle tempVehicle;

    @Override
    public boolean enterGarage(Car car) {

        if (tempVehicle == null) {
            tempVehicle = car;
            vehicleEnterText(car);
            return true;
        } else {
            System.out.println("No space in the garage. Wait when vehicle leave Garage");
            return false;
        }
    }

    @Override
    public boolean leaveGarage() {
        if (tempVehicle == null) {
            System.out.println("No vehicle in garage");
            return false;
        } else {
            vehicleLeaveText(tempVehicle);
            tempVehicle = null;
            return true;
        }

    }

}

class Garage extends MiniGarage implements garageFunctionGarage {
    private Truck tempTruck;

    public boolean enterGarage(Truck truck) {

        if (tempTruck == null) {
            tempTruck = truck;
            vehicleEnterText(truck);
            return true;
        } else {
            System.out.println("No space in the garage. Wait when vehicle leave Garage");
            return false;
        }
    }


}

class BigGarage implements garageFunctionBigGarage {
    private Vehicle tempVehicleOne;
    private Vehicle tempVehicleTwo;

    @Override
    public boolean enterGarage(Vehicle vehicleFirst, Vehicle vehicleSecond) {
        if (tempVehicleOne != null && tempVehicleTwo != null) {
            System.out.println("No space in the garage. Wait when vehicle leave Garage\"");
            return false;
        } else {
            vehicleEnterText(vehicleFirst, vehicleSecond);
            tempVehicleOne = vehicleFirst;
            tempVehicleTwo = vehicleSecond;
            return true;
        }
    }

    @Override
    public boolean enterGarage(Vehicle vehicle) {
        if (vehicle == tempVehicleOne || vehicle == tempVehicleTwo) {
            System.out.println("This vehicle was in garage");
            return false;
        } else if (tempVehicleOne != null && tempVehicleTwo != null) {
            System.out.println("Garage is full");
            return false;

        } else {
            if (tempVehicleOne == null) {
                tempVehicleOne = vehicle;
            } else {
                tempVehicleTwo = vehicle;
            }
            return true;
        }
    }

    @Override
    public boolean leaveGarage(Vehicle vehicle) {
        if (vehicle != tempVehicleOne || vehicle != tempVehicleTwo) {
            System.out.println("No this vehicle in garage");
            return false;
        } else {
            if (vehicle == tempVehicleOne) {
                tempVehicleOne = null;
            } else {
                tempVehicleTwo = null;
            }
            vehicleLeaveText(vehicle.getClass().getSimpleName());

            return true;
        }
    }

    @Override
    public boolean leaveGarage(Vehicle vehicleFirst, Vehicle vehicleSecond) {
        if ((vehicleFirst != tempVehicleOne || vehicleFirst != tempVehicleTwo)
                && (vehicleSecond != tempVehicleOne || vehicleSecond != tempVehicleTwo)) {
            System.out.println("In garage not was it's vehicles. Try another method");
            return false;
        } else {
            tempVehicleOne = null;
            tempVehicleTwo = null;
            vehicleLeaveText(vehicleFirst.getClass().getSimpleName(),
                    vehicleSecond.getClass().getSimpleName());

            return true;
        }
    }
}
