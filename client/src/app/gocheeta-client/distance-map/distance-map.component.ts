import { Component, OnInit } from '@angular/core';
import { DataTableColumns } from 'app/data-models/data-table-columns.model';
import { Branch, DistanceMap } from 'app/data-models/data.model';
import { GocheetaUtilsService } from 'app/gocheeta-utils.service';
import { environment } from 'environments/environment';
import { BaseApiService } from '../base-api.service';
declare var $: any;

@Component({
  selector: 'app-distance-map',
  templateUrl: './distance-map.component.html',
  styleUrls: ['./distance-map.component.css']
})
export class DistanceMapComponent implements OnInit {

  availableBranches: Branch[] = [];
  availableMappings: DistanceMap[] = [];
  availableColumns: DataTableColumns[] = [];
  newMap: DistanceMap = new DistanceMap();
  updateMap: DistanceMap = new DistanceMap();

  constructor(private httpService: BaseApiService, private goCheetaUtilService: GocheetaUtilsService) { }

  ngOnInit(): void {
    this.loadBranches();
    this.createColumns();
  }

  loadBranches(): void {
    this.availableBranches = [];
    this.goCheetaUtilService.displaySpiner();
    this.httpService.get(`${environment.backendHost}${environment.REST_URLS.BRANCHES}`, true).subscribe(response => {
      this.goCheetaUtilService.hideSpiner();
      response.forEach(res => {
        const newBranch = new Branch();
        newBranch.id = res.id;
        newBranch.name = res.name;
        this.availableBranches.push(newBranch);
      });
      this.loadAvailableMappings();
    }, err => {
      this.goCheetaUtilService.hideSpiner();
      this.goCheetaUtilService.displayUnsuccessMessage("Branch load is not success, please try again");
    });
  }

  loadAvailableMappings(): void {
    this.availableMappings = [];
    this.goCheetaUtilService.displaySpiner();
    this.httpService.get(`${environment.backendHost}${environment.REST_URLS.DISTANCE}`, true).subscribe(response => {
      this.goCheetaUtilService.hideSpiner();
      response.forEach(res => {
        const newMap = new DistanceMap();
        newMap.startCityId = res.startCityId;
        newMap.id = res.id;
        newMap.endCityId = res.endCityId;
        newMap.startCityName = this.availableBranches.find(branch => branch.id === newMap.startCityId)?.name;
        newMap.endCityName = this.availableBranches.find(branch => branch.id === newMap.endCityId)?.name;
        newMap.totalDistance = res.totalDistance;
        this.availableMappings.push(newMap);
      });
    }, err => {
      this.goCheetaUtilService.hideSpiner();
      this.goCheetaUtilService.displayUnsuccessMessage("Distance mappings are not loaded successfully, please try again");
    });
  }

  createNewMap(): void {
    const isValid = this.validate(this.newMap);
    if (isValid) {
      this.goCheetaUtilService.displaySpiner();
      this.httpService.post(`${environment.backendHost}${environment.REST_URLS.DISTANCE}`, true, this.newMap).subscribe(response => {
        this.goCheetaUtilService.displaySuccessMessage("Successfully insert new map");
        this.goCheetaUtilService.hideSpiner();
        this.loadAvailableMappings();
      }, err => {
        this.goCheetaUtilService.hideSpiner();
        this.goCheetaUtilService.displayUnsuccessMessage("Newly added distance map is not success, please try again");
      });
      $("#createNewMap").modal("hide");
    }
  }

  updateMapMethod(): void {
    const isValid = this.validate(this.updateMap, true);
    if (isValid) {
      this.goCheetaUtilService.displaySpiner();
      this.httpService.put(`${environment.backendHost}${environment.REST_URLS.DISTANCE}/${this.updateMap.id}`, true, this.updateMap).subscribe(response => {
        this.goCheetaUtilService.displaySuccessMessage("Update distance map successfully");
        this.goCheetaUtilService.hideSpiner();
        this.loadAvailableMappings();
      }, err => {
        this.goCheetaUtilService.hideSpiner();
        this.goCheetaUtilService.displayUnsuccessMessage("Updating distance map is not success, please try again");
      });
    }
  }

  deleteMapMethod(selectedMap: DistanceMap): void {
    this.goCheetaUtilService.displaySpiner();
    this.httpService.delete(`${environment.backendHost}${environment.REST_URLS.DISTANCE}/${selectedMap.id}`, true, null).subscribe(response => {
      this.goCheetaUtilService.displaySuccessMessage("Delete distance map successfully");
      this.goCheetaUtilService.hideSpiner();
      this.loadAvailableMappings();
    }, err => {
      this.goCheetaUtilService.hideSpiner();
      this.goCheetaUtilService.displayUnsuccessMessage("Delete distance map is not success, please try again");
    });
  }

  loadCreateMap(): void {
    this.newMap = new DistanceMap();
    $("#createNewMap").modal("show");
  }

  loadUpdateMap(selectedMap: DistanceMap): void {
    this.updateMap = selectedMap;
    $("#updateNewMap").modal("show");
  }

  private validate(map: DistanceMap, isUpdate = false): boolean {
    let isValid = true;
    if (map.endCityId === undefined) {
      this.goCheetaUtilService.displayUnsuccessMessage("Please select start city");
      isValid = false;
    }
    if (map.startCityId === undefined) {
      this.goCheetaUtilService.displayUnsuccessMessage("Please select end city");
      isValid = false;
    }
    if (map.totalDistance === undefined || map.totalDistance === 0) {
      this.goCheetaUtilService.displayUnsuccessMessage("Please provide valid distance");
      isValid = false;
    }
    if (map.endCityId === map.startCityId) {
      this.goCheetaUtilService.displayUnsuccessMessage("Both start and end cities are same");
      isValid = false;
    }
    if (isUpdate === false) {
      this.availableMappings.forEach(availableMap => {
        if (availableMap.startCityId === map.startCityId && availableMap.endCityId === map.endCityId) {
          this.goCheetaUtilService.displayUnsuccessMessage("This mapping is already excisting");
          isValid = false;
        }
      });
    }
    return isValid;
  }

  private createColumns(): void {
    this.availableColumns = [];

    const idColumn = new DataTableColumns();
    idColumn.fieldName = "id";
    idColumn.isSort = true;
    idColumn.label = "Id";
    idColumn.type = "data";
    idColumn.styleRules = "width: 10%";

    const startCityColumn = new DataTableColumns();
    startCityColumn.fieldName = "startCityName",
    startCityColumn.isSort = true;
    startCityColumn.label = "Start City";
    startCityColumn.type = "data";
    idColumn.styleRules = "width: 20%";

    const endCityColumn = new DataTableColumns();
    endCityColumn.fieldName = "endCityName",
    endCityColumn.isSort = true;
    endCityColumn.label = "End City";
    endCityColumn.type = "data";
    idColumn.styleRules = "width: 20%";

    const distance = new DataTableColumns();
    distance.fieldName = "totalDistance",
    distance.isSort = true;
    distance.label = "Total Distance";
    distance.type = "data";
    distance.styleRules = "width: 20%";

    const actionColumn = new DataTableColumns();
    actionColumn.type = "action";
    actionColumn.isSort = false;
    actionColumn.styleRules = "width: 20%";

    this.availableColumns.push(idColumn, startCityColumn, endCityColumn, distance, actionColumn);
  }

}
