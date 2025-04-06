public class Natural extends Person {
    float healthExpenses;
    public Natural(String name, float annualIncome, float healthExpenses) {
        super(name, annualIncome);
        this.healthExpenses = healthExpenses;
    }
    @Override
    public float getTaxes() {
        int taxRate = annualIncome < 20000 ? 15 : 25;
        float taxes = (annualIncome * taxRate / 100) - (healthExpenses / 2);
        return Math.max(taxes, 0);
    }
}
