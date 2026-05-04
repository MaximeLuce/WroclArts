import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { OrganizationService } from '../../../services/organizations';
import { OrganizationPayload } from '../../../models/organizations';

@Component({
  selector: 'app-organization-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './organization-form.html',
})
export class OrganizationFormComponent implements OnInit {
  private fb = inject(FormBuilder);
  private service = inject(OrganizationService);
  private route = inject(ActivatedRoute);
  private router = inject(Router);

  orgId?: number;

  // strongly typed form
  form = this.fb.nonNullable.group({
    orgId: [0],
    email: ['', Validators.required],
    nameOrga: ['', Validators.required],
    krsNumber: ['', Validators.required],
    subscriptionDate: ['', Validators.required],
    // default value + disabled
    status: [{ value: 'PendingVerification', disabled: true }, Validators.required],
  });

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');

    if (id) {
      this.orgId = Number(id);

      // if it's an update: we enable the status button
      this.form.controls.status.enable();

      this.service.getById(this.orgId).subscribe((organization) => {
        this.form.patchValue({
          orgId: organization.orgId,
          email: organization.email,
          nameOrga: organization.nameOrga,
          krsNumber: organization.krsNumber,
          subscriptionDate: organization.subscriptionDate,
          status: organization.status,
        });
      });
    }
  }

  submit() {
    if (this.form.invalid) return;

    // getRawValue() to get event disabled value
    const rawData = this.form.getRawValue();

    // extract orgId from data
    const { orgId, ...data } = rawData;

    if (this.orgId) {
      // update PUT
      this.service.update(this.orgId, data as OrganizationPayload).subscribe(() => {
        this.router.navigate(['/admin/listorganizations']);
      });
    } else {
      // create POST
      this.service.create(data as OrganizationPayload).subscribe(() => {
        this.router.navigate(['/admin/listorganizations']);
      });
    }
  }
}
