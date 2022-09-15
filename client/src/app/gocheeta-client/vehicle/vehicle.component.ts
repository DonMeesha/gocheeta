import { Component, OnInit } from '@angular/core';
import { DataTableColumns } from 'app/data-models/data-table-columns.model';
import { Category } from 'app/data-models/data.model';
import { GocheetaUtilsService } from 'app/gocheeta-utils.service';
import { environment } from 'environments/environment';
import { BaseApiService } from '../base-api.service';
declare var $: any;

@Component({
  selector: 'app-vehicle',
  templateUrl: './vehicle.component.html',
  styleUrls: ['./vehicle.component.css']
})
export class VehicleComponent implements OnInit {
  
  availableCategories: Category[] = [];
  newCategory: Category = new Category();
  updateCategory: Category = new Category();
  categoryTableColumns: DataTableColumns[] = [];

  constructor(private httpService: BaseApiService, private goCheetaUtilService:GocheetaUtilsService) {

  }

  ngOnInit(): void {
    this.loadAllCategories();
    this.createCategoryColumns();
  }

  loadAllCategories(): void {
    this.availableCategories = [];
    this.goCheetaUtilService.displaySpiner();
    this.httpService.get(`${environment.backendHost}${environment.REST_URLS.CATEGORY}`, true).subscribe(response => {
      this.goCheetaUtilService.hideSpiner();
      response.forEach(res => {
        let newCategory = new Category();
        newCategory.id = res.id;
        newCategory.name = res.name;
        newCategory.price = res.price;
        this.availableCategories.push(newCategory);
      });
    }, err => {
      this.goCheetaUtilService.displayUnsuccessMessage("Categories are not loaded properly");
      this.goCheetaUtilService.hideSpiner();
    });
  }

  createCategory(): void {
    const isValid = this.validateCategory(this.newCategory);
    if (isValid) {
      this.goCheetaUtilService.displaySpiner();
      this.httpService.post(`${environment.backendHost}${environment.REST_URLS.CATEGORY}`, true, this.newCategory).subscribe(res => {
        this.goCheetaUtilService.hideSpiner();
        this.goCheetaUtilService.displaySuccessMessage("Create new category successfully");
        this.loadAllCategories();
      }, err => {
        this.goCheetaUtilService.displayUnsuccessMessage("Creating new category is not work properly, please try again");
        this.goCheetaUtilService.hideSpiner();
      });
      $("#createCategory").modal("hide");
    }
  }

  updateCategoryMethod(): void {
    const isValid = this.validateCategory(this.updateCategory);
    if (isValid) {
      this.goCheetaUtilService.displaySpiner();
      this.httpService.put(`${environment.backendHost}${environment.REST_URLS.CATEGORY}/${this.updateCategory.id}`, true, this.updateCategory).subscribe(res => {
        this.goCheetaUtilService.hideSpiner();
        this.goCheetaUtilService.displaySuccessMessage("Update category successfully");
        this.loadAllCategories();
      }, err => {
        this.goCheetaUtilService.displayUnsuccessMessage("Update category is not work properly, please try again");
        this.goCheetaUtilService.hideSpiner();
      });
      $("#updateCategory").modal("hide");
    }
  }

  deleteCategory(selectedCategory: Category): void {
    this.httpService.delete(`${environment.backendHost}${environment.REST_URLS.CATEGORY}/${selectedCategory.id}`, true, null).subscribe(res => {
      this.goCheetaUtilService.hideSpiner();
      this.goCheetaUtilService.displaySuccessMessage("Delete category successfully");
      this.loadAllCategories();
    }, err => {
      this.goCheetaUtilService.displayUnsuccessMessage("Delete category is not work properly, please try again");
      this.goCheetaUtilService.hideSpiner();
    });
  }

  loadUpdateCategoryModal(selectedCategory: Category) {
    this.updateCategory = selectedCategory;
    $("#updateCategory").modal("show");
  }

  loadCategoryModal(): void {
    this.newCategory = new Category();
    $("#createCategory").modal("show");
  }

  validateCategory(category: Category): boolean {
    let isValid = true;
    if (category.name === undefined || category.name.trim() === "") {
      isValid = false;
      this.goCheetaUtilService.displayUnsuccessMessage("Provide valid category name");
    }
    if (category.price === undefined || category.price === 0) {
      isValid = false;
      this.goCheetaUtilService.displayUnsuccessMessage("Provide valid category price");
    }
    return isValid;
  }

  private createCategoryColumns(): void {
    const categoryName = new DataTableColumns();
    categoryName.type = "data";
    categoryName.fieldName = "name";
    categoryName.isSort = true;
    categoryName.label = "Category Name";
    categoryName.styleRules = "width: 40%";

    const categoryPrice = new DataTableColumns();
    categoryPrice.type = "data";
    categoryPrice.isSort = true;
    categoryPrice.fieldName = "price";
    categoryPrice.label = "Category Price";
    categoryPrice.styleRules = "width: 40%";

    const actionColumn = new DataTableColumns();
    actionColumn.type = "action";
    actionColumn.isSort = false;
    actionColumn.styleRules = "width: 20%";

    this.categoryTableColumns.push(categoryName, categoryPrice, actionColumn);
  }


}
