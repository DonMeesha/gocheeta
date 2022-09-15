// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
  production: false,
  backendHost: "http://localhost:8081/",
  fileManager: "http://localhost:8082/",
  REST_URLS: {
    BOOKING: "api/bookings",
    BRANCHES: "api/branches",
    CATEGORY: "api/category",
    CUSTOMER: "api/customer",
    DISTANCE: "api/distance",
    DRIVER: "api/driver",
    AUTH: "authenticate",
    STREET: "api/street",
    VEHICLE: "api/vehicle",
    IMAGE_MANAGER: "image/save"
  }
};
