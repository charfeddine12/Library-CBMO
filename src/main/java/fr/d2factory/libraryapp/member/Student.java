package fr.d2factory.libraryapp.member;

import fr.d2factory.libraryapp.library.CostBorrowException;
import fr.d2factory.libraryapp.library.WalletNotEnoughException;
import fr.d2factory.librayapp.utility.ParamLibrary;

public class Student extends Member {

	private boolean firstYear;

	public Student(boolean firstYear, int wallet) {
		super(wallet);
		this.firstYear = firstYear;
		NumberDaysKeeping = ParamLibrary.getDaysBeforeLateStudent();
	}

	public boolean isFirstYear() {
		return firstYear;
	}

	@Override
	public void payBook(int numberOfDays) {
		int cost = 0;

		if (firstYear)
			numberOfDays -= ParamLibrary.getFirstYearFree();
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
		if (numberOfDays <= ParamLibrary.getDaysBeforeLateStudent()) {
			costOfBorrow = numberOfDays * ParamLibrary.getStudentPriceAfterLate();
		} else {
			costOfBorrow = numberOfDays * ParamLibrary.getStudentPriceAfterLate()
					+ ParamLibrary.getMembrePriceBeforeLate()
							* (numberOfDays - ParamLibrary.getStudentPriceAfterLate());
		}
		return costOfBorrow;
	}

}
