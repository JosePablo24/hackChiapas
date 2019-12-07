'use strict'

/** @typedef {import('@adonisjs/framework/src/Request')} Request */
/** @typedef {import('@adonisjs/framework/src/Response')} Response */
/** @typedef {import('@adonisjs/framework/src/View')} View */

/**
 * Resourceful controller for interacting with historials
 */
  const Database = use('Database')
  const Historial = use('App/Models/Historial')

class HistorialController {

  async getByUser({params, request, response}){
    let { id } = params
    let Historial = Database.select('*').where('user_id',id)
  }

  /**
   * Show a list of all historials.
   * GET historials
   *
   * @param {object} ctx
   * @param {Request} ctx.request
   * @param {Response} ctx.response
   * @param {View} ctx.view
   */
  async index ({ request, response, view }) {
    let historial = Historial.all()
    return response.status(200).json(historial)
  }

  /**
   * Render a form to be used for creating a new historial.
   * GET historials/create
   *
   * @param {object} ctx
   * @param {Request} ctx.request
   * @param {Response} ctx.response
   * @param {View} ctx.view
   */
  async create ({ request, response, view }) {
  }

  /**
   * Create/save a new historial.
   * POST historials
   *
   * @param {object} ctx
   * @param {Request} ctx.request
   * @param {Response} ctx.response
   */
  async store ({ request, response }) {
    let historia_data = request.all()
    let historial = await Historial.create(historia_data)
    return response.status(201).json({
      message : 'Saved in Historial'
    })
  }

  /**
   * Display a single historial.
   * GET historials/:id
   *
   * @param {object} ctx
   * @param {Request} ctx.request
   * @param {Response} ctx.response
   * @param {View} ctx.view
   */
  async show ({ params, request, response, view }) {
    let { id } = params
    let historial = await Historial.findOrFail(id)
    return response.status(200).json(historial)
  }

  /**
   * Render a form to update an existing historial.
   * GET historials/:id/edit
   *
   * @param {object} ctx
   * @param {Request} ctx.request
   * @param {Response} ctx.response
   * @param {View} ctx.view
   */
  async edit ({ params, request, response, view }) {
  }

  /**
   * Update historial details.
   * PUT or PATCH historials/:id
   *
   * @param {object} ctx
   * @param {Request} ctx.request
   * @param {Response} ctx.response
   */
  async update ({ params, request, response }) {
    let { id } = params
    let data_historial  = request.all()
    let historial = await Historial.findOrFail(id)

    historial.merge()
    await historial.save()

    return response.status(201).json({message : 'Historial updated'})
  }

  /**
   * Delete a historial with id.
   * DELETE historials/:id
   *
   * @param {object} ctx
   * @param {Request} ctx.request
   * @param {Response} ctx.response
   */
  async destroy ({ params, request, response }) {
    let { id } = params
    let historia = Historial.findOrFail(id)
    historia.delete()
    
    return response.status(201).json({message: 'Historia deleted'})
  }
}

module.exports = HistorialController
