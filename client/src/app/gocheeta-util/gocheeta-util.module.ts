import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DataTableComponent } from './data-table/data-table.component';
import { NgxDataTableModule } from "angular-9-datatable";

@NgModule({
  declarations: [
    DataTableComponent,
  ],
  imports: [
    CommonModule,
    NgxDataTableModule,
  ],
  exports: [
    DataTableComponent,
  ]
})
export class GocheetaUtilModule { }
