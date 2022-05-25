describe('My First Test', () => {
  it('Login into Conduit', () => {
    cy.visit('https://rx-devtest.com/')
            cy.get(':nth-child(2) > .nav-link')
            .click()
            cy.get(':nth-child(1) > .form-control')
               .type('exclamationpointforfun@gmail.com')
            cy.get(':nth-child(2) > .form-control')
               .type('password')
            cy.get('.btn')
                .click()

                cy.get(':nth-child(4) > .nav-link')
                .should('have.attr', 'href', '#/@sandra')


  })
})
