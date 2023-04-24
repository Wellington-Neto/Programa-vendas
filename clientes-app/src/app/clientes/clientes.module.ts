import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClientesRoutingModule } from './clientes-routing.module';
import { ClientesformComponent } from './clientes-form/clientes-form.component';


@NgModule({
  declarations: [ClientesformComponent],
  imports: [
    CommonModule,
    ClientesRoutingModule
  ], exports: [
    ClientesformComponent
  ]
})
export class ClientesModule { }
