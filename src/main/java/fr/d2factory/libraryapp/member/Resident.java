package fr.d2factory.libraryapp.member;

import fr.d2factory.libraryapp.library.CostBorrowException;
import fr.d2factory.libraryapp.library.WalletNotEnoughException;
import fr.d2factory.librayapp.utility.ParamLibrary;

public class Resident extends Member {

	public Resident(int wallet) {
		super(wallet);
		NumberDaysKeeping = ParamLibrary.getDaysBeforeLateResident();
	}

	@Override
	public void payBook(int numberOfDays) {
		int cost = 0;
		cost = costOfBorrowBook(numberOfDays);
		if (cost < 0) {
			throw new CostBorrowException("negative cost value: " + cost);
		}
		if (cost > getWallet()) {
			throw new WalletNotEnoughException("Insufficient wallet balance: " + getWallet() + ", cost: " + cost);
		}
		setWallet(getWallet() - cost);
	}

	@Override
	public int costOfBorrowBook(int numberOfDays) {
		int costOfBorrow;
		if (numberOfDays <= ParamLibrary.getDaysBeforeLateResident()) {
			costOfBorrow = numberOfDays * ParamLibrary.getMembrePriceBeforeLate();
		} else {
			costOfBorrow = numberOfDays * ParamLibrary.getResidentPriceAfterLate()
					+ ParamLibrary.getMembrePriceBeforeLate()
							* (numberOfDays - ParamLibrary.getDaysBeforeLateResident());
		}
		return costOfBorrow;

	}
}
