import { Component, OnInit } from '@angular/core';
import { NgxSpinnerService } from "ngx-spinner"
import { AlertService } from "ngx-alerts"
import { Branch, StreetAddress } from 'app/data-models/data.model';
import { BaseApiService } from '../base-api.service';
import { environment } from 'environments/environment';
import { DataTableColumns } from 'app/data-models/data-table-columns.model';

declare var $: any

@Component({
  selector: 'app-branch',
  templateUrl: './branch.component.html',
  styleUrls: ['./branch.component.css']
})
export class BranchComponent implements OnInit {

  newBranch: Branch = new Branch();
  updateBranch: Branch = new Branch();
  availableBranches: Branch[] = [];
  branchDataTableColumns: Array<DataTableColumns> = new Array<DataTableColumns>();

  newStreet: StreetAddress = new StreetAddress();
  updateStreet: StreetAddress = new StreetAddress();
  availableStreets: StreetAddress[] = [];
  streetDataTableColumns: DataTableColumns[] = [];

  constructor(private spinner: NgxSpinnerService, private alertService: AlertService, private httpService: BaseApiService) { }

  ngOnInit(): void {
    this.prepareBranchTableColumns();
    this.prepareStreetDataTableColumns();
    this.loadAllBranches();
    this.loadAllStreets();
  }

  loadCreateNewBranchModal(): void{
    this.newBranch = new Branch();
    $("#createBranchModal").modal("show");
  }

  loadCreateStreetModal(): void {
    this.newStreet = new StreetAddress();
    $("#createStreetModal").modal("show");
  }

  createNewBranch(): void {
    const isValid = this.validationBranch(this.newBranch);
    if (isValid) {
      this.spinner.show();
      this.httpService.post(`${environment.backendHost}${environment.REST_URLS.BRANCHES}`, true, this.newBranch).subscribe(response => {
        this.spinner.hide();
        this.alertService.success("Branch added Successfully");
        this.loadAllBranches();
      }, error => {
        this.spinner.hide();
        this.alertService.danger("Adding branch is not success, please try again");
      });
      $("#createBranchModal").modal("hide");
    }
  }

  createNewStreet(): void {
    const isValid = this.validationStreet(this.newStreet);
    if (isValid) {
      const preparedObject = this.prepareStreetRequestObject(this.newStreet);
      this.spinner.show();
      this.httpService.post(`${environment.backendHost}${environment.REST_URLS.STREET}`, true, preparedObject).subscribe(response => {
        this.spinner.hide();
        this.alertService.success("Street added Successfully");
        this.loadAllStreets();
      }, err => {
        this.spinner.hide();
        this.alertService.danger("street not created successfully, please try again");
      });
      $("#createStreetModal").modal("hide");
    }
  }

  loadAllBranches(): void {
    this.availableBranches = [];
    this.spinner.show();
    this.httpService.get(`${environment.backendHost}${environment.REST_URLS.BRANCHES}`, true).subscribe(response => {
      this.spinner.hide();
      response.forEach(branch => {
        let newBranch = new Branch();
        newBranch.id = branch.id;
        newBranch.name = branch.name;
        this.availableBranches.push(newBranch);
      });
    }, err => {
      this.spinner.hide();
      this.alertService.danger("Loading branches is not success, please try again");
    });
  }

  loadAllStreets(): void {
    this.availableStreets = [];
    this.spinner.show();
    this.httpService.get(`${environment.backendHost}${environment.REST_URLS.STREET}`, true).subscribe(response => {
      this.spinner.hide();
      response.forEach(street => {
        let newStreet = new StreetAddress();
        newStreet.id = street.id;
        newStreet.branchId = street.branchDetails.id;
        newStreet.branchName = street.branchDetails.name;
        newStreet.name = street.name;
        console.log(newStreet);
        this.availableStreets.push(newStreet);
      });
    }, err => {
      this.spinner.hide();
      this.alertService.danger("Loading streets is not success, please try again");
    })
  }

  deleteBranch(selectedBranch: Branch): void {
    this.spinner.show();
    this.httpService.delete(`${environment.backendHost}${environment.REST_URLS.BRANCHES}/${selectedBranch.id}`, true, null).subscribe(response => {
      this.spinner.hide();
      this.alertService.success("Branch has deleted successfully");
      this.loadAllBranches();
    }, err => {
      this.spinner.hide();
      this.alertService.danger("Branch delete is not success, please try again");
    });
  }

  editBranch(selectedBranch: Branch) {
    this.updateBranch = selectedBranch;
    $("#updateBranch").modal("show");
  }

  updateExcistingBranch(): void {
    const isValid = this.validationBranch(this.updateBranch);
    if (isValid) {
      $("#updateBranch").modal("hide");
      this.spinner.show();
      this.httpService.put(`${environment.backendHost}${environment.REST_URLS.BRANCHES}/${this.updateBranch.id}`, true, this.updateBranch).subscribe(response => {
        this.spinner.hide();
        this.alertService.success("Branch has updated successfully");
        this.loadAllBranches();
      }, err => {
        this.spinner.hide();
        this.alertService.danger("Branch update is not success, please try again");
      });
    }
  }

  deleteStreet(street: StreetAddress): void {
    this.spinner.show();
    this.httpService.delete(`${environment.backendHost}${environment.REST_URLS.STREET}/${street.id}`, true, null).subscribe(response => {
      this.spinner.hide();
      this.alertService.success("Street has deleted successfully");
      this.loadAllStreets();
    }, err => {
      this.spinner.hide();
      this.alertService.danger("Street has not deleted successfully");
    })
  }

  loadEditStreetMenu(street: StreetAddress): void {
    this.updateStreet = street;
    $("#editStreetModal").modal("show");
  }

  editStreet(): void {
    const isValid = this.validationStreet(this.updateStreet);
    if (isValid) {
      this.httpService.put(`${environment.backendHost}${environment.REST_URLS.STREET}/${this.updateStreet.id}`, true, this.prepareStreetRequestObject(this.updateStreet)).subscribe(response => {
        this.spinner.hide();
        this.alertService.success("Street has updated successfully");
        this.loadAllStreets();
      }, err => {
        this.spinner.hide();
        this.alertService.danger("Street has not updated successfully, please try again");
      });
      $("#editStreetModal").modal("hide");
    }
  }

  private validationBranch(branch :Branch): boolean {
    let isValid = true;
    if (branch.name.trim() === "" || branch.name === undefined) {
      this.alertService.danger("Fill branch name");
      isValid = false;
    }
    return isValid;
  }

  private validationStreet(street: StreetAddress): boolean {
    let isValid = true;
    if (street.name.trim() === "" || street.name === undefined) {
      this.alertService.danger("Fill street name");
      isValid = false;
    }
    if (street.branchId === undefined) {
      this.alertService.danger("Select a branch is mandetory");
      isValid = false;
    }
    return isValid;
  }

  private prepareBranchTableColumns(): void {
    let idColumn = new DataTableColumns();
    idColumn.fieldName = "id",
    idColumn.label = "Id";
    idColumn.isSort = true;
    idColumn.type = "data";
    idColumn.styleRules = "width: 40%";

    let nameColumn = new DataTableColumns();
    nameColumn.fieldName = "name";
    nameColumn.label = "Branch Name";
    nameColumn.isSort = true;
    nameColumn.type = "data";
    nameColumn.styleRules = "width: 40%";

    let actionColumn = new DataTableColumns();
    actionColumn.type = "action";
    actionColumn.isSort = false;
    actionColumn.styleRules = "width: 20%";

    this.branchDataTableColumns.push(idColumn, nameColumn, actionColumn);
  }

  private prepareStreetDataTableColumns(): void {
    let idColumn = new DataTableColumns();
    idColumn.fieldName = "id",
    idColumn.label = "Id";
    idColumn.isSort = true;
    idColumn.type = "data";
    idColumn.styleRules = "width: 20%";

    let nameColumn = new DataTableColumns();
    nameColumn.fieldName = "name";
    nameColumn.label = "Street Name";
    nameColumn.isSort = true;
    nameColumn.type = "data";
    nameColumn.styleRules = "width: 20%";

    let branchColumn = new DataTableColumns();
    branchColumn.fieldName = "branchName";
    branchColumn.label = "Branch Name";
    branchColumn.isSort = true;
    branchColumn.type = "data";
    branchColumn.styleRules = "width: 40%";

    let actionColumn = new DataTableColumns();
    actionColumn.type = "action";
    actionColumn.isSort = false;
    actionColumn.styleRules = "width: 20%";

    this.streetDataTableColumns.push(idColumn, nameColumn, branchColumn, actionColumn);
  }

  private prepareStreetRequestObject(streetObject: StreetAddress): any {
    return {
      name: streetObject.name,
      branchDetails: {
        id: streetObject.branchId
      }
    }
  }

}
