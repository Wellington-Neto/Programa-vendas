import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ClientesformComponent } from './clientes-form/clientes-form.component'


const routes: Routes = [
  { path: 'clientes-form', component: ClientesformComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientesRoutingModule { }
