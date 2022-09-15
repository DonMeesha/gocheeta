export class Branch {
    id: number;
    name: String;
}

export class StreetAddress {
    id: number;
    name: String;
    branchId: number;
    branchName: String;
}

export class DistanceMap {
    id: number;
    startCityId: number;
    endCityId: number;
    totalDistance: number;
    startCityName: String;
    endCityName: String;
}

export class Vehicle {
    id: number;
    vehicleNumber: String;
    imagePath: String;
    brand: String;
    year: number;
    capacity: number;
    categoryDetails: Category = new Category();
    branchDetails: Branch = new Branch();
    driverList: Driver[] = [];
}

export class Category {
    id: number;
    name: String;
    price: number;
}

export class Driver {
    id: number;
    firstName: String;
    lastName: String;
    email: String;
    contactNumber: String;
    password: String;
    imageUrl: String;
    branchDetails: Branch = new Branch();
    vehicleList: Vehicle[] = [];
}