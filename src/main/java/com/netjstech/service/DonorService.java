package com.netjstech.service;

import java.util.List;

import com.netjstech.entities.Donation;
import com.netjstech.entities.Donor;
import com.netjstech.exception.NoDonationFoundException;
import com.netjstech.exception.UniqueConstraintViolationException;

public interface DonorService {
	public boolean addDonor(Donor donor) throws UniqueConstraintViolationException;

	public boolean addDonation(Donation donation);

	public List<Donation> findDonation() throws NoDonationFoundException;

}
